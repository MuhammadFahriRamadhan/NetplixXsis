package com.xsis.netplix.core.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

data class BelongsToCollection(
    var backdropPath: String?,
    var id: Int?,
    var name: String?,
    var posterPath: String?
)