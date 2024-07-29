package com.mahmulp.core.domain.usecase

import com.mahmulp.core.domain.model.Story
import com.mahmulp.core.domain.repository.IStoryRepository

class StoryInteractor(private val storyRepository: IStoryRepository): StoryUseCase {

    override fun getAllStory() = storyRepository.getAllStory()

    override fun getFavoriteStory() = storyRepository.getFavoriteStory()

    override fun setFavoriteStory(story: Story, state: Boolean) = storyRepository.setFavoriteStory(story, state)

    override suspend fun login(email: String, password: String) = storyRepository.login(email, password)

}