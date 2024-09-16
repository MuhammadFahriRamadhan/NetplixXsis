package com.xsis.netplix.view.search

import androidx.lifecycle.ViewModelProvider
import com.xsis.netplix.core.base.BaseActivity
import com.xsis.netplix.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity<ActivitySearchBinding,SearchViewModel>() {

    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): SearchViewModel {
       return ViewModelProvider(this@SearchActivity)[SearchViewModel::class.java]
    }

    override fun initView() {

    }

    override fun addListener() {

    }

    override fun addObserve() {

    }
}