package com.example.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie WHERE isMovie = 1")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isMovie = 0")
    fun getAllSeries(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    fun getFavorite(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isMovie = 1 AND name LIKE '%'  || :search || '%'")
    fun searchMovie(search: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isMovie = 0 AND name LIKE '%'  || :search || '%'")
    fun searchSeries(search: String): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateFavorite(movie: MovieEntity)
}