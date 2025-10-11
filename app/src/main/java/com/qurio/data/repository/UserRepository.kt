package com.qurio.data.repository

interface UserRepository {

    suspend fun getUserCharacter(): Int
    suspend fun setUserCharacter(characterId: Int)
    suspend fun getUserPoints(): Int
    suspend fun getTotalPoints(points: Int)
    suspend fun getLives(): Int
    suspend fun updateLives(lives: Int)
    suspend fun getAwards(): Int
    suspend fun updateAwards(awards: Int)
    suspend fun getStreak(): Int
    suspend fun updateStreak(streak: Int)

}