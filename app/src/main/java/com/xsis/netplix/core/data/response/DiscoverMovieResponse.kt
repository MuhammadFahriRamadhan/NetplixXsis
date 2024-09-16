package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class DiscoverMovieResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var resultResponses: List<ResultResponse?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)