package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface AchievementsDao {

    @Query("SELECT * FROM achievements")
    fun getAchievements(): Int

    @Query("UPDATE achievements SET isAchieved = 1 WHERE id = :achievementId")
    fun unlockAchievement(achievementId: Int)

    @Query("SELECT isAchieved FROM achievements WHERE id = :achievementId")
    fun isAchievementUnlocked(achievementId: Int): Boolean

    @Query("SELECT * FROM achievements WHERE id = :achievementId")
    fun getAchievementDetails(achievementId: Int): String
}