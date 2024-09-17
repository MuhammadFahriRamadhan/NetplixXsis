package com.xsis.netplix.view.detail

import com.xsis.netplix.core.base.BaseViewModel
import com.xsis.netplix.core.domain.model.DetailMovie
import com.xsis.netplix.core.domain.model.Trailer
import com.xsis.netplix.core.domain.repository.MovieRepository
import com.xsis.netplix.core.util.SingleLiveEvent
import com.xsis.netplix.core.util.getGeneralErrorServer
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class DetailMovieViewModel : BaseViewModel(),KoinComponent {
    private val movieRepository : MovieRepository = get()
    val movieDetailEvent  = SingleLiveEvent<DetailMovie?>()
    val trailerVideoEvent = SingleLiveEvent<Trailer?>()

    fun getDetailMovie(movieId : String){
        safeScopeFun {
            handleFailure(it.getGeneralErrorServer())
        }.launch {
            movieRepository.getDetailMovie(movieId)
                .onStart { isLoadingLiveData.postValue(true) }
                .onCompletion { isLoadingLiveData.postValue(false) }
                .collect{ detailMovie ->
                    movieDetailEvent.postValue(detailMovie)
                }
        }
    }

    fun getTrailerMovie(movieId: String) {
        safeScopeFun {
            handleFailure(it.getGeneralErrorServer())
        }.launch {
            movieRepository.getTrailer(movieId)
                .onStart { isLoadingLiveData.postValue(true) }
                .onCompletion { isLoadingLiveData.postValue(false) }
                .collect{ trailer ->
                    trailerVideoEvent.postValue(trailer)
                }
        }
    }
}