package com.example.pixelplax.ui.detail

import androidx.lifecycle.ViewModel
import com.example.core.domain.model.Movie
import com.example.core.domain.usecase.MovieUseCase

class DetailViewModel(movieUseCase: MovieUseCase): ViewModel() {

    fun setFavorite(movie: Movie, newStatus: Boolean) {
     //   movieUseCase.setFavorite(movie, newStatus)
    }

}