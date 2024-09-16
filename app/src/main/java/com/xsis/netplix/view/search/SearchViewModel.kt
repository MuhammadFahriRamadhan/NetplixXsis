package com.xsis.netplix.view.search

import com.xsis.netplix.core.base.BaseViewModel
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.domain.repository.MovieRepository
import com.xsis.netplix.core.util.SingleLiveEvent
import com.xsis.netplix.core.util.getGeneralErrorServer
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.Koin
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class SearchViewModel : BaseViewModel(),KoinComponent {

    val movieRepository : MovieRepository = get()
    val bodyParam =  HashMap<String,Any>()
    val movieEvent = SingleLiveEvent<List<ResultMovie?>?>()

    fun getMovieGenre(query : String) {
        bodyParam.clear()
        bodyParam.put("q",query)
        safeScopeFun {
            handleFailure(it.getGeneralErrorServer())
        }.launch {
            movieRepository.getMovies(bodyParam).onStart {
                isLoadingLiveData.postValue(true)
            }.onCompletion {
                isLoadingLiveData.postValue(false)
            }.collect{
                movieEvent.postValue(it)
            }
        }
    }
}