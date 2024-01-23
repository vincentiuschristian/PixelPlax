package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val movieId: String,
    val name: String,
    val overview: String,
    val voteCount: Int,
    val voteAverage: Int,
    val popularity: Int,
    val firstAirDate: String,
    val posterPath: String,
    val isFavorite: Boolean,
): Parcelable