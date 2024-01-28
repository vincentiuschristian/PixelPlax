package com.example.core.data.source.remote

import android.util.Log
import com.example.core.BuildConfig
import com.example.core.data.source.remote.network.ApiResponse
import com.example.core.data.source.remote.network.ApiService
import com.example.core.data.source.remote.response.ResultsItemMovie
import com.example.core.data.source.remote.response.ResultsItemTV
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val key = BuildConfig.API_KEY

    suspend fun getAllMovie(): Flow<ApiResponse<List<ResultsItemMovie>>> {
        return flow {
            try {
                val response = apiService.getMovie(key)
                val dataArray = response.results
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getAllTvShow(): Flow<ApiResponse<List<ResultsItemTV>>> {
        return flow {
            try {
                val response = apiService.getTvShow(key)
                val dataArray = response.results
                emit(ApiResponse.Success(dataArray))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}