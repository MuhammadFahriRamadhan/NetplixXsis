package com.xsis.netplix.core.domain.model


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

data class ProductionCompany(
    var id: Int?,
    var logoPath: String?,
    var name: String?,
    var originCountry: String?
)