package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT characterId FROM users WHERE id = 1")
    suspend fun getUserCharacters(): Int

    @Query("UPDATE users SET characterId = :characterId WHERE id = 1")
    suspend fun updateUserCharacters(characterId: Int)

    @Query("SELECT totalPoints FROM users WHERE id = 1")
    suspend fun getTotalPoints(): Int

    @Query("UPDATE users SET totalPoints = :points WHERE id = 1")
    suspend fun updateTotalPoints(points: Int)

    @Query("SELECT lives FROM users WHERE id = 1")
    suspend fun getLives(): Int

    @Query("UPDATE users SET lives = :lives WHERE id = 1")
    suspend fun updateLives(lives: Int)

    @Query("SELECT awards FROM users WHERE id = 1")
    suspend fun getAwards(): Int

    @Query("UPDATE users SET awards = :awards WHERE id = 1")
    suspend fun updateAwards(awards: Int)

    @Query("SELECT streak FROM users WHERE id = 1")
    suspend fun getStreak(): Int

    @Query("UPDATE users SET streak = :streak WHERE id = 1")
    suspend fun updateStreak(streak: Int)

}