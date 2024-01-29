package com.example.pixelplax.ui.tvShow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalCoroutinesApi
@FlowPreview
class TvShowViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val tvShow = movieUseCase.getAllTvShow().asLiveData()

}