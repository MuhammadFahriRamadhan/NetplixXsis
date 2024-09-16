package com.xsis.netplix.core.data.repositoryimpl

import android.util.Log
import com.xsis.netplix.BuildConfig
import com.xsis.netplix.core.data.remote.ApiService
import com.xsis.netplix.core.domain.model.DetailMovie
import com.xsis.netplix.core.domain.model.Genre
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.domain.repository.MovieRepository
import com.xsis.netplix.core.util.ProxyRetrofitQueryMap
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepositoryImpl(private val apiService: ApiService) : MovieRepository {

    override suspend fun getGenres(): Flow<List<Genre?>?> {
        return flow {
            try {
                val response = apiService.getGenres()
                emit(response.genreResponses?.map { it?.toGenre() })
            }catch (e : Exception){
                throw Throwable(e.message)
            }
        }
    }

    override suspend fun getMovies(queryParam: Map<String, Any>): Flow<List<ResultMovie?>?> {
        return flow {
            try {
                val response = apiService.getMovies(ProxyRetrofitQueryMap(queryParam))
                emit(response.resultResponses?.map { it?.toResultMovie()  })
            }catch (e : Exception){
                throw  Throwable(e.message)
            }
        }
    }

    override suspend fun getDetailMovie(movieId: String): Flow<DetailMovie?> {
        return flow {
            try {
                val response = apiService.getDetailMovie(movieId)
                emit(response.toDetailMovie())
            }catch (e : Exception) {
                throw Throwable(e.message)
            }
        }
    }
}