package com.xsis.netplix.core.base

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.xsis.netplix.core.exception.Failure

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>  : Fragment() {
     protected var binding: VB? = null
     protected var viewModel: VM? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  getViewBinding()
        viewModel  = getViewModelClass()
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addObserve()
        addListener()
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
                Toast.makeText(requireContext(), "No Connection", Toast.LENGTH_SHORT).show()
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