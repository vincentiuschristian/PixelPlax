package com.example.core.domain.usecase

import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository

class MovieInteractor(private val movieRepository: IMovieRepository) : MovieUseCase {
    override fun getAllMovie() = movieRepository.getAllMovie()

    override fun getAllTvShow() = movieRepository.getAllTvShow()

    override fun getFavorite() = movieRepository.getFavorite()

    override fun setFavorite(movie: Movie, state: Boolean) =
        movieRepository.setFavorite(movie, state)
}