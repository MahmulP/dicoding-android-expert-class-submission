package com.mahmulp.core.domain.usecase

import com.mahmulp.core.data.Resource
import com.mahmulp.core.data.source.remote.response.LoginResponse
import com.mahmulp.core.domain.model.Story
import kotlinx.coroutines.flow.Flow

interface StoryUseCase {
    fun getAllStory(): Flow<Resource<List<Story>>>
    fun getFavoriteStory(): Flow<List<Story>>
    fun setFavoriteStory(story: Story, state: Boolean)
    suspend fun login(email: String, password: String): LoginResponse
}