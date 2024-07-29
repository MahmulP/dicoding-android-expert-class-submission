package com.mahmulp.storyapp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.mahmulp.core.domain.usecase.StoryUseCase

class HomeViewModel(storyUseCase: StoryUseCase) : ViewModel() {
    val story = storyUseCase.getAllStory().asLiveData()
}