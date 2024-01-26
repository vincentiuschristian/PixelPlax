package com.example.pixelplax.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
@ExperimentalCoroutinesApi
@FlowPreview
class SeriesViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val series = movieUseCase.getAllSeries().asLiveData()

    val queryChannelSeries = MutableStateFlow("")

    val searchResult = queryChannelSeries
        .debounce(300)
        .distinctUntilChanged()
        .filter {
            it.trim().isNotEmpty()
        }
        .mapLatest {
            movieUseCase.searchMovie(it)
        }
        .asLiveData()


}