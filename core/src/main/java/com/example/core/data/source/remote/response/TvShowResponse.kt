package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsItemTV>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class ResultsItemTV(

    @field:SerializedName("first_air_date")
    val firstAirDate: String?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("original_name")
    val originalName: String?,

    @field:SerializedName("popularity")
    val popularity: Double?,

    @field:SerializedName("vote_average")
    val voteAverage: Double?,

    @field:SerializedName("name")
    val name: String?,

    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("vote_count")
    val voteCount: Int?
)