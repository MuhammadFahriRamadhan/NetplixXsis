package com.xsis.netplix.core.data.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.xsis.netplix.core.domain.model.ResultMovie

@Keep
data class ResultResponse(
    @SerializedName("adult")
    var adult: Boolean?,
    @SerializedName("backdrop_path")
    var backdropPath: String?,
    @SerializedName("genre_ids")
    var genreIds: List<Int?>?,
    @SerializedName("id")
    var id: Int?,
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
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("video")
    var video: Boolean?,
    @SerializedName("vote_average")
    var voteAverage: Double?,
    @SerializedName("vote_count")
    var voteCount: Int?
) {
    fun toResultMovie() : ResultMovie {
        return ResultMovie(adult, backdropPath, genreIds, id, originalLanguage, originalTitle, overview, popularity, posterPath, releaseDate, title, video, voteAverage, voteCount)
    }
}