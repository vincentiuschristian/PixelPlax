package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @field:SerializedName("page")
    val page: Int,

    @field:SerializedName("total_pages")
    val totalPages: Int,

    @field:SerializedName("results")
    val results: List<ResultsItemMovie>,

    @field:SerializedName("total_results")
    val totalResults: Int
)

data class ResultsItemMovie(

    @field:SerializedName("release_date")
    val releaseDate: String?,

    @field:SerializedName("overview")
    val overview: String?,

    @field:SerializedName("poster_path")
    val posterPath: String?,

    @field:SerializedName("title")
    val title: String?,

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
