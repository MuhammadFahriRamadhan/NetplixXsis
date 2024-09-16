package com.xsis.netplix.core.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xsis.netplix.core.exception.Failure
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.plus

abstract class BaseViewModel  : ViewModel() {
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val failureLiveData: MutableLiveData<Failure> = MutableLiveData()

    protected fun handleFailure(failure: Failure) {
        isLoadingLiveData.postValue( false)
        failureLiveData.postValue(failure)
    }

    protected fun safeScopeFun(error: (Throwable) -> Unit): CoroutineScope {
        return viewModelScope + CoroutineExceptionHandler { coroutineContext, throwable ->
            error.invoke(throwable)
        }
    }

}