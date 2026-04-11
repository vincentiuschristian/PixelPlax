package com.example.core.data.source

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ResultsItemMovie
import com.example.core.data.source.remote.response.ResultsItemTV
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItemMovie>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemMovie>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<ResultsItemMovie>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getAllTvShow(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItemTV>>() {

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemTV>>> {
                return remoteDataSource.getAllTvShow()
            }

            override suspend fun saveCallResult(data: List<ResultsItemTV>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertMovie(tvShowList)
            }

        }.asFlow()

    override fun search(query: String): Flow<List<Movie>> {
        return localDataSource.getSearch(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavorite(): Flow<List<Movie>> {
        return localDataSource.getAllFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override suspend fun setFavorite(movie: Movie, state: Boolean) {
        withContext(Dispatchers.IO) {
            val movieEntity = DataMapper.mapDomainToEntity(movie)
            localDataSource.setFavorite(movieEntity, state)
        }
    }
}