package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItem
import com.example.core.domain.model.Movie

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id ?: 0,
                name = it.originalName ?: "",
                firstAirDate = it.firstAirDate ?: "",
                overview = it.overview ?: "",
                popularity = it.popularity ?: 0.0,
                posterPath = it.posterPath ?: "",
                voteAverage = it.voteAverage ?: 0.0,
                voteCount = it.voteCount ?: 0,
                isFavorite = false,
                isMovie = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapSeriesResponsesToEntities(input: List<ResultsItem>): List<MovieEntity> {
        val seriesList = ArrayList<MovieEntity>()
        input.map {
            val series = MovieEntity(
                movieId = it.id ?: 0,
                name = it.originalName ?: "",
                firstAirDate = it.firstAirDate ?: "",
                overview = it.overview ?: "",
                popularity = it.popularity ?: 0.0,
                posterPath = it.posterPath ?: "",
                voteAverage = it.voteAverage ?: 0.0,
                voteCount = it.voteCount ?: 0,
                isFavorite = false,
                isMovie = false
            )
            seriesList.add(series)
        }
        return seriesList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                name = it.name,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = it.isFavorite,
                isMovie = it.isMovie
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        name = input.name,
        firstAirDate = input.firstAirDate,
        overview = input.overview,
        popularity = input.popularity,
        posterPath = input.posterPath,
        voteAverage = input.voteAverage,
        voteCount = input.voteCount,
        isFavorite = input.isFavorite,
        isMovie = input.isMovie
    )

}