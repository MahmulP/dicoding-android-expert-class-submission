package com.mahmulp.storyapp.di

import com.mahmulp.core.auth.SessionManager
import com.mahmulp.core.auth.UserRepository
import com.mahmulp.core.domain.usecase.StoryInteractor
import com.mahmulp.core.domain.usecase.StoryUseCase
import com.mahmulp.storyapp.authentication.AuthenticationViewModel
import com.mahmulp.storyapp.detail.DetailViewModel
import com.mahmulp.storyapp.home.HomeViewModel
import com.mahmulp.storyapp.viewmodel.FavoriteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<StoryUseCase> { StoryInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { AuthenticationViewModel(get(), get()) }
    viewModel { FavoriteViewModel(get()) }
}

val storageModule = module {
    factory {
        SessionManager(get())
    }

    factory {
        UserRepository(get())
    }
}