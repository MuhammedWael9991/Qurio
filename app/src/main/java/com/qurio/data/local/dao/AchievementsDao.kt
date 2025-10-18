package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qurio.data.local.entity.AchievementsEntity

@Dao
interface AchievementsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inertAll(achievements: List<AchievementsEntity>)

    @Query("SELECT COUNT(*) FROM achievements")
    suspend fun getCount(): Int
    @Query("SELECT * FROM achievements")
    suspend fun getAchievements(): List<AchievementsEntity>
    @Query("UPDATE achievements SET isAchieved = 1 WHERE id = :id")
    suspend fun unlockAchievement(id: Int)

    @Query("SELECT isAchieved FROM achievements WHERE id = :achievementId")
    suspend fun isUnlocked(achievementId: Int): Boolean

}