package com.xsis.netplix.core.base

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.xsis.netplix.R
import com.xsis.netplix.core.util.cloneDefaultTheme

abstract class BaseDialogFragment<VB : ViewBinding,VM : ViewModel> : DialogFragment() {
    protected var binding: VB? = null
    var viewModel: VM? = null
    protected lateinit var cloneLayoutInflater: LayoutInflater


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog = Dialog(requireContext(), theme)

    override fun getTheme(): Int = R.style.Theme_Dialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        cloneLayoutInflater = inflater.cloneDefaultTheme(requireActivity())
        if (binding == null) binding = getUiBinding()
        viewModel = getViewModelClass()
        dialog?.requestWindowFeature(STYLE_NO_TITLE)
        isCancelable = false
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        addObserve()
        addListener()
        setupBackPressHandling()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    abstract fun getUiBinding(): VB
    abstract fun getViewModelClass(): VM
    abstract fun onBackPressedEnabled(): Boolean
    abstract fun initView()
    abstract fun addListener()
    abstract fun addObserve()


    private fun setupBackPressHandling() {
        if (onBackPressedEnabled()) {
            dialog?.setOnKeyListener { _, keyCode, _ ->
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss()
                    true
                } else {
                    false
                }
            }
        }
    }

}