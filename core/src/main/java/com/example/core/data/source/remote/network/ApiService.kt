package com.example.core.data.source.remote.network

import com.example.core.data.source.remote.response.MovieResponse
import com.example.core.data.source.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getMovie(
        @Query("api_key") apiKey: String
    ): MovieResponse

    @GET("tv/popular")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String
    ): TvShowResponse


}