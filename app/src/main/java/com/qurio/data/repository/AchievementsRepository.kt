package com.qurio.data.repository

import com.qurio.data.local.entity.AchievementsEntity

interface AchievementsRepository {

    suspend fun initAchievements()
    suspend fun getAchievements(): List<AchievementsEntity>
    suspend fun unlockAchievement(achievementId: Int)

    suspend fun isUnlocked(achievementId: Int): Boolean
}