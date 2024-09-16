package com.xsis.netplix.core.domain.repository

import com.xsis.netplix.core.domain.model.Genre
import com.xsis.netplix.core.domain.model.DetailMovie
import com.xsis.netplix.core.domain.model.ResultMovie
import kotlinx.coroutines.flow.Flow


interface MovieRepository {
    suspend fun getGenres() : Flow<List<Genre?>?>
    suspend fun getMovies(queryParam: Map<String, Any>) : Flow<List<ResultMovie?>?>
    suspend fun getDetailMovie(movieId : String) : Flow<DetailMovie?>
}