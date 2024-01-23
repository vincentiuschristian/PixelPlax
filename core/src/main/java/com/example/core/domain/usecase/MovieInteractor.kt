package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllTourism() = movieRepository.getAllMovie()

    override fun getAllSeries() = movieRepository.getAllSeries()
    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) =
        movieRepository.setFavorite(movie, state)
}