package com.xsis.netplix.core.data.remote

import com.xsis.netplix.core.data.response.DetailMovieResponse
import com.xsis.netplix.core.data.response.DiscoverMovieResponse
import com.xsis.netplix.core.data.response.GenreMovieResponse
import com.xsis.netplix.core.data.response.GenreResponse
import com.xsis.netplix.core.data.response.Response
import com.xsis.netplix.core.util.ProxyRetrofitQueryMap
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {

    @GET("genre/movie/list")
    suspend fun getGenres() : GenreMovieResponse

    @GET("discover/movie")
    suspend fun getMovies(@QueryMap queryParam: ProxyRetrofitQueryMap) : DiscoverMovieResponse

    @GET("/movie/{movie_id}")
    suspend fun getDetailMovie(@Path("movie_id") movieId : String) : DetailMovieResponse

}