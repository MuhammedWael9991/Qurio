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

}