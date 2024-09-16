package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.ProductionCountry

@Keep
data class ProductionCountryResponse(
    @SerializedName("iso_3166_1")
    var iso31661: String?,
    @SerializedName("name")
    var name: String?
) {
    fun toProductionCountry() : ProductionCountry {
        return ProductionCountry(iso31661, name)
    }
}