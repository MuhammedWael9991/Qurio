package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AchievementsDao {

    @Query("SELECT * FROM achievements")
    suspend fun getAchievements(): Int

    @Query("UPDATE achievements SET isAchieved = 1 WHERE id = :achievementId")
    suspend fun unlockAchievement(achievementId: Int)

    @Query("SELECT isAchieved FROM achievements WHERE id = :achievementId")
    suspend fun isAchievementUnlocked(achievementId: Int): Boolean

    @Query("SELECT * FROM achievements WHERE id = :achievementId")
    suspend fun getAchievementDetails(achievementId: Int): String
}