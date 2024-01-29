package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: Int?,
    val originalName: String?,
    val overview: String?,
    val voteCount: Int?,
    val voteAverage: Double?,
    val popularity: Double?,
    val firstAirDate: String?,
    val posterPath: String?,
    val isFavorite: Boolean,
    val isMovie: Boolean,
) : Parcelable