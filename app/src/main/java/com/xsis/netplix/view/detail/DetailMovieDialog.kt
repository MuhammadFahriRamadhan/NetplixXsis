package com.xsis.netplix.view.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import com.xsis.netplix.R
import com.xsis.netplix.core.base.BaseActivity
import com.xsis.netplix.core.base.BaseDialogFragment
import com.xsis.netplix.core.exception.Failure
import com.xsis.netplix.core.util.extractQueryParam
import com.xsis.netplix.core.util.loadImage
import com.xsis.netplix.core.util.showSnackBar
import com.xsis.netplix.databinding.DialogDetailMovieBinding

class DetailMovieDialog(private val idMovie: String) : BaseDialogFragment<DialogDetailMovieBinding,DetailMovieViewModel>() {


    var youTubePlayerView : YouTubePlayerView? = null
    var youTubePlayer : YouTubePlayer? = null

    override fun getUiBinding(): DialogDetailMovieBinding {
        return DialogDetailMovieBinding.inflate(layoutInflater)
    }

    override fun getViewModelClass(): DetailMovieViewModel {
        return ViewModelProvider(this)[DetailMovieViewModel::class.java]
    }

    override fun onBackPressedEnabled(): Boolean {
        return true
    }

    override fun initView() {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(resources.getColor(R.color.grey_1)))
        youTubePlayerView = binding?.detailMoviePlayer
        viewModel?.getDetailMovie(idMovie)
        viewModel?.getTrailerMovie(idMovie)
    }

    override fun addListener() {

    }

    override fun addObserve() {
        viewModel?.run {
            isLoadingLiveData.observe(this@DetailMovieDialog){
                binding?.progressBar?.isVisible = it
            }
            failureLiveData.observe(this@DetailMovieDialog){
                when(it){
                    is Failure.ServerError -> {
                        showSnackBar(binding?.root?.rootView,it.message, Color.RED)
                    }
                    else -> {
                        showSnackBar(binding?.root?.rootView,it.toString(), Color.RED)
                    }
                }
            }
            movieDetailEvent.observe(this@DetailMovieDialog){detailMovie ->
                binding?.run {
                    detailMovie?.let {
                        detailMovieTextTitle.text = it.originalTitle
                        detailMovieToolbarTxtName.text = it.originalTitle
                        detailMovieTextGenre.text = it.genres?.firstOrNull()?.name.toString()
                        detailMovieTextRelaseDate.text = it.releaseDate
                        detailMovieImgPoster.loadImage(it.posterPath.orEmpty())
                        detailMovieTextAge.text = if (it.adult == true) "R 13+" else "R 18+"
                        detailMovieTextRating.text = it.voteAverage.toString()
                        detailMovieRatingBar.rating  = it.voteAverage?.toFloat() ?: 0.0F
                    }
                }
            }
            trailerVideoEvent.observe(this@DetailMovieDialog){
               val videoIdKey =  it?.resultTrailerResponses?.firstOrNull()?.key
                initVideoPlayer(videoIdKey)
            }
        }
    }

    private fun initVideoPlayer(videoIdKey: String?) {
        youTubePlayerView?.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(initializedYouTubePlayer: YouTubePlayer) {
                youTubePlayer = initializedYouTubePlayer
                youTubePlayer?.cueVideo(videoIdKey.toString(), 0F)
            }
        })
    }

    companion object {
      fun createInstance(idMovie : String) : DetailMovieDialog {
          return  DetailMovieDialog(idMovie)
      }
    }
}