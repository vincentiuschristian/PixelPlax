package com.example.core.data.source

import com.example.core.data.source.local.LocalDataSource
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.response.ResultsItemMovie
import com.example.core.data.source.remote.response.ResultsItemSeries
import com.example.core.domain.model.Movie
import com.example.core.domain.repository.IMovieRepository
import com.example.core.utils.AppExecutors
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.map

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {
    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItemMovie>>(),
            Flow<Resource<List<Movie>>> {
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

            override suspend fun collect(collector: FlowCollector<Resource<List<Movie>>>) = Unit

        }.asFlow()

    override fun getAllSeries(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<ResultsItemSeries>>(),
            Flow<Resource<List<Movie>>> {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllSeries().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<ResultsItemSeries>>> =
                remoteDataSource.getAllSeries()


            override suspend fun saveCallResult(data: List<ResultsItemSeries>) {
                val movieList = DataMapper.mapSeriesResponsesToEntities(data)
                localDataSource.insertMovie(movieList)
            }

            override suspend fun collect(collector: FlowCollector<Resource<List<Movie>>>) = Unit

        }.asFlow()

    override fun searchMovie(query: String): Flow<List<Movie>>{
        return localDataSource.searchMovie(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun searchSeries(query: String): Flow<List<Movie>> {
        return localDataSource.searchSeries(query).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getFavorite(): Flow<List<Movie>> {
        return localDataSource.getAllFavorite().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavorite(movie: Movie, state: Boolean) {
       val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavorite(movieEntity, state) }
    }
}