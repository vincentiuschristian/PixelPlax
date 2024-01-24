package com.example.pixelplax.di

import com.example.core.domain.usecase.MovieInteractor
import com.example.core.domain.usecase.MovieUseCase
import com.example.pixelplax.ui.detail.DetailViewModel
import com.example.pixelplax.ui.favorite.FavoriteViewModel
import com.example.pixelplax.ui.movie.MovieViewModel
import com.example.pixelplax.ui.series.SeriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { SeriesViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}