package com.xsis.netplix.core.domain.repository

import com.xsis.netplix.core.data.response.DiscoverMovieResponse
import com.xsis.netplix.core.data.response.TrailerResponse
import com.xsis.netplix.core.domain.model.Genre
import com.xsis.netplix.core.domain.model.DetailMovie
import com.xsis.netplix.core.domain.model.ResultMovie
import com.xsis.netplix.core.domain.model.Trailer
import com.xsis.netplix.core.util.ProxyRetrofitQueryMap
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap


interface MovieRepository {
    suspend fun getGenres() : Flow<List<Genre?>?>
    suspend fun getMovies(queryParam: Map<String, Any>) : Flow<List<ResultMovie?>?>
    suspend fun getDetailMovie(movieId : String) : Flow<DetailMovie?>
    suspend fun searchMovies(queryParam: Map<String, Any>) : Flow<List<ResultMovie?>?>
    suspend fun getTrailer(movieId : String) : Flow<Trailer>
}