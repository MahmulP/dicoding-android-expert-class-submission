package com.mahmulp.core.data.source.local

import com.mahmulp.core.data.source.local.entity.StoryEntity
import com.mahmulp.core.data.source.local.room.StoryDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(private val storyDao: StoryDao) {
    fun getAllStory(): Flow<List<StoryEntity>> = storyDao.getAllStory()

    fun getFavoriteStory(): Flow<List<StoryEntity>> = storyDao.getFavoriteStory()

    suspend fun insertStory(storyList: List<StoryEntity>) = storyDao.insertStory(storyList)

    fun setFavoriteTourism(story: StoryEntity, newState: Boolean) {
        story.isFavorite = newState
        storyDao.updateFavoriteStory(story)
    }
}