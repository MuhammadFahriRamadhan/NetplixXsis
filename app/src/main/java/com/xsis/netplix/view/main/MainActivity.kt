package com.xsis.netplix.view.main

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mikepenz.fastadapter.adapters.GenericFastItemAdapter
import com.xsis.netplix.core.base.BaseActivity
import com.xsis.netplix.core.util.LinePagerIndicator
import com.xsis.netplix.databinding.ActivityMainBinding
import com.xsis.netplix.view.main.adapter.BannerAdapter
import com.xsis.netplix.view.common.adapter.MovieAdapter
import com.xsis.netplix.view.main.adapter.TitleAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    val fastAdapterBanner by  lazy { GenericFastItemAdapter() }
    val fastAdapterMovie by lazy { GenericFastItemAdapter() }
    var previousGenre = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel?.getMovieGenre()

    }

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): MainViewModel {
      return ViewModelProvider(this@MainActivity)[MainViewModel::class.java]
    }

    override fun initView() {
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
        }
    }
}