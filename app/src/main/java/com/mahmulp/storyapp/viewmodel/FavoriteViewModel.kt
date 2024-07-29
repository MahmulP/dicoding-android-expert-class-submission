package com.mahmulp.storyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mahmulp.core.domain.usecase.StoryUseCase

class FavoriteViewModel(storyUseCase: StoryUseCase): ViewModel() {
    val favoriteStory = storyUseCase.getFavoriteStory().asLiveData()
}