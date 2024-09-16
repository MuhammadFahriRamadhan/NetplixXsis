package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.SpokenLanguage

@Keep
data class SpokenLanguageResponse(
    @SerializedName("english_name")
    var englishName: String?,
    @SerializedName("iso_639_1")
    var iso6391: String?,
    @SerializedName("name")
    var name: String?
) {
    fun toSpokenLanguage() : SpokenLanguage {
        return SpokenLanguage(englishName, iso6391, name)
    }
}