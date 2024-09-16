package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class GenreMovieResponse(
    @SerializedName("genres")
    var genreResponses: List<GenreResponse?>?
)