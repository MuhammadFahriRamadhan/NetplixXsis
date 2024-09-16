package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.Genre

@Keep
data class GenreResponse(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
) {
    fun toGenre() : Genre {
        return Genre(id,name)
    }
}