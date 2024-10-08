package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.DetailMovie

@Keep
data class DetailMovieResponse(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("belongs_to_collection")
    var belongsToCollectionResponse: BelongsToCollectionResponse?,
    @SerializedName("budget")
    var budget: Int?,
    @SerializedName("genres")
    var genres: List<GenreResponse?>?,
    @SerializedName("homepage")
    var homepage: String?,
    @SerializedName("id")
    var id: Int?,
    @SerializedName("imdb_id")
    var imdbId: String?,
    @SerializedName("origin_country")
    var originCountry: List<String?>?,
    @SerializedName("original_language")
    var originalLanguage: String?,
    @SerializedName("original_title")
    var originalTitle: String?,
    @SerializedName("overview")
    var overview: String?,
    @SerializedName("popularity")
    var popularity: Double?,
    @SerializedName("poster_path")
    var posterPath: String?,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompanyResponse?>?,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountryResponse?>?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("revenue")
    var revenue: Int?,
    @SerializedName("runtime")
    var runtime: Int?,
    @SerializedName("spoken_languages")
    var spokenLanguageResponses: List<SpokenLanguageResponse?>?,
    @SerializedName("status")
    var status: String?,
    @SerializedName("tagline")
    var tagline: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) {
    fun toDetailMovie() : DetailMovie {
        return DetailMovie(adult,backdropPath,belongsToCollectionResponse?.BelongsToCollection(),budget,genres?.map { it?.toGenre() },homepage,id,imdbId,originCountry,originalLanguage,originalTitle,overview,popularity,posterPath,productionCompanies?.map { it?.toProductionCompany() },productionCountries?.map { it?.toProductionCountry() },releaseDate,revenue,runtime,spokenLanguageResponses?.map { it?.toSpokenLanguage() },status,tagline,title, video, voteAverage, voteCount)
    }
}