package com.qurio.data.repository

interface AchievementsRepository {

    suspend fun getAchievements(): Int
    suspend fun unlockAchievement(achievementId: Int)
    suspend fun isAchievementUnlocked(achievementId: Int): Boolean
    suspend fun getAchievementDetails(achievementId: Int): String

}