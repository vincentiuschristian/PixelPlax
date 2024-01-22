package com.example.core.domain.repository

import com.example.core.data.source.Resource
import com.example.core.data.source.local.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovie(): Flow<Resource<List<MovieEntity>>>

}