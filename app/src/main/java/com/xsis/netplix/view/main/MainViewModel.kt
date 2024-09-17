package com.xsis.netplix.view.main

import com.xsis.netplix.core.base.BaseViewModel
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.domain.repository.MovieRepository
import com.xsis.netplix.core.util.SingleLiveEvent
import com.xsis.netplix.core.util.getGeneralErrorServer
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class MainViewModel : BaseViewModel(),KoinComponent {
    val movieRepository : MovieRepository = get()
    val bodyParam =  HashMap<String,Any>()
    val movieWithGenresEvent = SingleLiveEvent<List<ResultMovie?>?>()

    fun getMovieGenre() {
        safeScopeFun {
            handleFailure(it.getGeneralErrorServer())
       }.launch {
            combine(movieRepository.getMovies(bodyParam),movieRepository.getGenres()){ movies,genres ->
                val genreMap = genres?.associateBy({it?.id},{it?.name})
                val  movieWithGenres =  movies?.map { it.apply { it?.genres = it?.genreIds?.mapNotNull { genreMap!![it] } }  }
                movieWithGenres
            }.onStart {
                isLoadingLiveData.postValue(true)
            }.onCompletion {
                isLoadingLiveData.postValue(false)
            }.collect{ movieWithGenres->
                movieWithGenresEvent.postValue(movieWithGenres?.sortedBy { it?.genres?.first() })
            }
        }
    }
}