package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.BelongsToCollection

@Keep
data class BelongsToCollectionResponse(
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?,
    @SerializedName("poster_path")
    var posterPath: String?
) {
    fun BelongsToCollection() : BelongsToCollection {
        return BelongsToCollection(backdropPath, id, name, posterPath)
    }
}