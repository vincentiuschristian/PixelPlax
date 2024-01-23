package com.example.core.data.source.local

import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val movieDao: MovieDao) {

    fun getAllMovie(): Flow<List<MovieEntity>> = movieDao.getAllMovie()

    fun getAllSeries(): Flow<List<MovieEntity>> = movieDao.getAllSeries()

    fun getAllFavorite(): Flow<List<MovieEntity>> = movieDao.getFavorite()

    fun insertMovie(movieList: List<MovieEntity>) = movieDao.insertMovie(movieList)

    fun setFavorite(movie: MovieEntity, newState: Boolean){
        movie.isFavorite = newState
        movieDao.updateFavorite(movie)
    }

}