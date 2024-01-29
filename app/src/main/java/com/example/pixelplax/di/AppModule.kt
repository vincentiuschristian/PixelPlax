package com.example.pixelplax.di

import com.example.core.domain.usecase.MovieInteractor
import com.example.core.domain.usecase.MovieUseCase
import com.example.pixelplax.ui.detail.DetailViewModel
import com.example.pixelplax.ui.movie.MovieViewModel
import com.example.pixelplax.ui.search.SearchViewModel
import com.example.pixelplax.ui.tvShow.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

@FlowPreview
@ExperimentalCoroutinesApi
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { SearchViewModel(get()) }
}