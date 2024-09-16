package com.xsis.netplix.core.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.xsis.netplix.core.exception.Failure

abstract class BaseActivity<VB : ViewBinding, VM : ViewModel> : AppCompatActivity() {

    var binding: VB? = null
    var viewModel: VM? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        viewModel = getViewModelClass()
        setContentView(binding?.root)

        initView()
        addListener()
        addObserve()
    }

    abstract fun getViewBinding(): VB
    abstract fun getViewModelClass(): VM
    abstract fun initView()
    abstract fun addListener()
    abstract fun addObserve()
    open fun handleFailure(failure: Failure?) {
        when (failure) {
            Failure.BadRequest -> {
                Log.i("Tag", "Bad Request")
            }
            Failure.NetworkConnection -> {
                Toast.makeText(this, "No Connection", Toast.LENGTH_SHORT).show()
            }
            is Failure.ServerError -> {
                Log.i("TAGED", "handleFailure: "+failure.message)
            }
            else -> {
                Log.i("Tag", "Internal Server Error")
            }
        }
    }
}
