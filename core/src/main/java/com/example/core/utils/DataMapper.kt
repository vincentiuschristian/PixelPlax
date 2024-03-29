package com.example.core.utils

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.response.ResultsItemMovie
import com.example.core.data.source.remote.response.ResultsItemTV
import com.example.core.domain.model.Movie

object DataMapper {

    fun mapMovieResponsesToEntities(input: List<ResultsItemMovie>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                name = it.title,
                firstAirDate = it.releaseDate,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false,
                isMovie = true
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapTvShowResponsesToEntities(input: List<ResultsItemTV>): List<MovieEntity> {
        val tvShowList = ArrayList<MovieEntity>()
        input.map {
            val tvShow = MovieEntity(
                movieId = it.id,
                name = it.originalName,
                firstAirDate = it.firstAirDate,
                overview = it.overview,
                popularity = it.popularity,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                voteCount = it.voteCount,
                isFavorite = false,
                isMovie = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                originalName = it.name,
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
        name = input.originalName,
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