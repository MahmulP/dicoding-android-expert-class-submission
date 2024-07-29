package com.mahmulp.storyapp.detail

import androidx.lifecycle.ViewModel
import com.mahmulp.core.domain.model.Story
import com.mahmulp.core.domain.usecase.StoryUseCase

class DetailViewModel(private val storyUseCase: StoryUseCase) : ViewModel() {
    fun setFavoriteStory(story: Story, newStatus: Boolean) =
        storyUseCase.setFavoriteStory(story, newStatus)
}