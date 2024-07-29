package com.mahmulp.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.mahmulp.core.data.source.local.entity.StoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoryDao {
    @Query("SELECT * FROM story")
    fun getAllStory(): Flow<List<StoryEntity>>

    @Query("SELECT * FROM story where isFavorite = 1")
    fun getFavoriteStory(): Flow<List<StoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(tourism: List<StoryEntity>)

    @Update
    fun updateFavoriteStory(tourism: StoryEntity)
}