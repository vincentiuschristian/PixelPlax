package com.example.pixelplax.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.launch

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavorite(movie: Movie, newStatus: Boolean) {
        viewModelScope.launch {
            movieUseCase.setFavorite(movie, newStatus)
        }
    }

}