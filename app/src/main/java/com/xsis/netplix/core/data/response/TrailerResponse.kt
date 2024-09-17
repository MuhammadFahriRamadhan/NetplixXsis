package com.xsis.netplix.core.data.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.xsis.netplix.core.domain.model.Trailer

@Keep
data class TrailerResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("results")
    val resultTrailerResponses: List<ResultTrailerResponse>?
) {
    fun toTrailer(): Trailer {
        return Trailer(id, resultTrailerResponses?.map { it.toResultTrailer() })
    }
}