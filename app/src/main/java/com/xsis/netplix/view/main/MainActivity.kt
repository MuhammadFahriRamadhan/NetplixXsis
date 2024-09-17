package com.xsis.netplix.view.main

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.xsis.netplix.core.base.BaseActivity
import com.xsis.netplix.core.exception.Failure
import com.xsis.netplix.core.util.LinePagerIndicator
import com.xsis.netplix.core.util.showSnackBar
import com.xsis.netplix.databinding.ActivityMainBinding
import com.xsis.netplix.view.main.adapter.BannerAdapter
import com.xsis.netplix.view.common.adapter.MovieAdapter
import com.xsis.netplix.view.detail.DetailMovieDialog
import com.xsis.netplix.view.main.adapter.TitleAdapter
import com.xsis.netplix.view.search.SearchActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    val fastAdapterBanner by  lazy { GenericFastItemAdapter() }
    val fastAdapterMovie by lazy { GenericFastItemAdapter() }
    var previousGenre = ""

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): MainViewModel {
      return ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    override fun initView() {
        viewModel?.getMovieGenre()
        initRecycleView()
    }

    private fun initRecycleView() {
        binding?.run {
            rcvBanner.run {
                layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
                adapter = fastAdapterBanner
                addItemDecoration(LinePagerIndicator())
                PagerSnapHelper().attachToRecyclerView(this)
            }
            rcvMovie.run {
                layoutManager =  FlexboxLayoutManager(this@MainActivity).apply {
                    justifyContent = JustifyContent.FLEX_START
                }
                adapter = fastAdapterMovie
            }
        }
    }

    override fun addListener() {
        binding?.run {
            imgSearch.setOnClickListener {
                startActivity(SearchActivity.createInstance(this@MainActivity))
            }

            fastAdapterMovie.onClickListener = { _, _, item, _ ->
                if (item is MovieAdapter) {
                    DetailMovieDialog.createInstance(item.resultMovie?.id.toString()).show(supportFragmentManager,"DetailMovieDialog")
                }
                false
            }

            fastAdapterBanner.onClickListener = { _, _, item, _ ->
                if (item is BannerAdapter) {
                    DetailMovieDialog.createInstance(item.resultMovie?.id.toString()).show(supportFragmentManager,"DetailMovieDialog")
                }
                false
            }

        }
    }

    override fun addObserve() {
        viewModel?.run {
            fastAdapterMovie.clear()
            fastAdapterBanner.clear()
            movieWithGenresEvent.observe(this@MainActivity){ movieWithGenres ->
                Log.i("TAGED", "addObserve: "+movieWithGenres)
                movieWithGenres?.take(5)?.forEach { movie ->
                    fastAdapterBanner.add(BannerAdapter(movie))
                }
                movieWithGenres?.forEach { movie ->
                    Log.i("TAGED", "addObserve:sd "+movie)
                    if(movie?.genres?.first() != previousGenre){
                        fastAdapterMovie.add(TitleAdapter(movie?.genres?.first().toString()))
                    }
                    fastAdapterMovie.add(MovieAdapter(movie))
                    previousGenre = movie?.genres?.first().toString()
                }
            }

            isLoadingLiveData.observe(this@MainActivity){
                binding?.progressBar?.isVisible = it
            }
            failureLiveData.observe(this@MainActivity){
                when(it){
                    is Failure.ServerError -> {
                        showSnackBar(binding?.root?.rootView,it.message,Color.RED)
                    }
                    else -> {
                        showSnackBar(binding?.root?.rootView,it.toString(),Color.RED)
                    }
                }

            }


        }
    }
}