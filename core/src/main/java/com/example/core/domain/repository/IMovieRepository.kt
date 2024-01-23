package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<MovieEntity>>>
    fun getAllSeries(): Flow<Resource<List<MovieEntity>>>
    fun getFavorite(): Flow<Resource<List<MovieEntity>>>
    fun setFavorite(favorite: Movie, state: Boolean): Flow<Resource<List<MovieEntity>>>

}