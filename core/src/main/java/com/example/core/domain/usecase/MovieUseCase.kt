package com.example.core.domain.usecase

import com.example.core.data.source.Resource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getAllSeries(): Flow<Resource<List<Movie>>>
    fun searchMovie(query: String): Flow<List<Movie>>
    fun searchSeries(query: String): Flow<List<Movie>>
    fun getFavorite(): Flow<List<Movie>>
    fun setFavorite(movie: Movie, state: Boolean)


}