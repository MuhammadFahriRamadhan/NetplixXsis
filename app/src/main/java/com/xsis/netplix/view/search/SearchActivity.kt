package com.xsis.netplix.view.search

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mikepenz.fastadapter.GenericFastAdapter
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.xsis.netplix.core.base.BaseActivity
import com.xsis.netplix.core.exception.Failure
import com.xsis.netplix.core.util.showSnackBar
import com.xsis.netplix.core.util.textChanges
import com.xsis.netplix.databinding.ActivitySearchBinding
import com.xsis.netplix.view.common.adapter.MovieAdapter
import com.xsis.netplix.view.detail.DetailMovieDialog
import com.xsis.netplix.view.search.adapter.EmptyAdapter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class SearchActivity : BaseActivity<ActivitySearchBinding,SearchViewModel>() {
    val fastAdapter by lazy { GenericFastItemAdapter() }
    override fun getViewBinding(): ActivitySearchBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): SearchViewModel {
       return ViewModelProvider(this@SearchActivity)[SearchViewModel::class.java]
    }

    override fun initView() {
        initRecycleView()
    }

    private fun initRecycleView() {
        binding?.run {
            generalRcv.run {
                layoutManager =  FlexboxLayoutManager(this@SearchActivity).apply {
                    justifyContent = JustifyContent.FLEX_START
                }
                adapter = fastAdapter
            }
        }
    }

    override fun addListener() {
        binding?.run {
            appbar.edtxSearch.textChanges().onEach {
                if (it?.isNotEmpty() == true) {
                    viewModel?.getMovieGenre(it.toString())
                }else{
                    viewModel?.getMovies()
                }
            }.launchIn(lifecycleScope)

            fastAdapter.onClickListener = { _, _, item, _ ->
                if (item is MovieAdapter) {
                    DetailMovieDialog.createInstance(item.resultMovie?.id.toString()).show(supportFragmentManager,"DetailMovieDialog")
                }
                false
            }
        }
    }

    override fun addObserve() {
        viewModel?.run {
            isLoadingLiveData.observe(this@SearchActivity){
                binding?.generalSearchProgressBar?.isVisible = it
            }

            failureLiveData.observe(this@SearchActivity){
                when(it){
                    is Failure.ServerError -> {
                        showSnackBar(binding?.root?.rootView,it.message, Color.RED)
                    }
                    else -> {
                        showSnackBar(binding?.root?.rootView,it.toString(), Color.RED)
                    }
                }

            }

            movieEvent.observe(this@SearchActivity){movies ->
                fastAdapter.clear()
                if (movies?.isNotEmpty() == true) {
                    movies.map {
                        fastAdapter.add(MovieAdapter(it))
                    }
                }else{
                    fastAdapter.add(EmptyAdapter("Movies Not found"))
                }
            }

        }
    }

    companion object {
        fun createInstance(source : Activity) : Intent {
            return Intent(source,SearchActivity::class.java)
        }
    }
}