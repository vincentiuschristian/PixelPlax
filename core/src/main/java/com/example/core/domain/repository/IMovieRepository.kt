package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getAllTvShow(): Flow<Resource<List<Movie>>>
    fun search(query: String): Flow<List<Movie>>
    fun getFavorite(): Flow<List<Movie>>
    fun setFavorite(movie: Movie, state: Boolean)

}