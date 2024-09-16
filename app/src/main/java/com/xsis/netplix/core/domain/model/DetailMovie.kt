package com.xsis.netplix.core.domain.model


import androidx.annotation.Keep

@Keep
data class DetailMovie(
    var adult: Boolean?,
    var backdropPath: String?,
    var belongsToCollection: BelongsToCollection?,
    var budget: Int?,
    var genres: List<Genre?>?,
    var homepage: String?,
    var id: Int?,
    var imdbId: String?,
    var originCountry: List<String?>?,
    var originalLanguage: String?,
    var originalTitle: String?,
    var overview: String?,
    var popularity: Double?,
    var posterPath: String?,
    var productionCompanies: List<ProductionCompany?>?,
    var productionCountries: List<ProductionCountry?>?,
    var releaseDate: String?,
    var revenue: Int?,
    var runtime: Int?,
    var spokenLanguages: List<SpokenLanguage?>?,
    var status: String?,
    var tagline: String?,
    var title: String?,
    var video: Boolean?,
    var voteAverage: Double?,
    var voteCount: Int?
)