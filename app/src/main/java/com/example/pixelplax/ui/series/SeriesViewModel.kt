package com.example.pixelplax.ui.series

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.usecase.MovieUseCase

class SeriesViewModel(movieUseCase: MovieUseCase) : ViewModel() {

    val series = movieUseCase.getAllSeries().asLiveData()

}