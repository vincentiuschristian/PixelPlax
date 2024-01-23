package com.example.core.data.source

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.local.entity.MovieEntity
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import kotlinx.coroutines.flow.Flow

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<MovieEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getAllSeries(): Flow<Resource<List<MovieEntity>>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): Flow<Resource<List<MovieEntity>>> {
        TODO("Not yet implemented")
    }

    override fun setFavorite(favorite: Movie, state: Boolean): Flow<Resource<List<MovieEntity>>> {
        TODO("Not yet implemented")
    }
}