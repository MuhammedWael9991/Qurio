package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qurio.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)
    @Query("SELECT COUNT(*) FROM users")
    suspend fun getCount(): Int
    @Query("SELECT * FROM users WHERE id = 1")
    suspend fun getUser(): UserEntity
    @Query("UPDATE users SET totalPoints = totalPoints + :point WHERE id = 1")
    suspend fun updateUserPoints(point: Int)
    @Query("SELECT totalPoints FROM users WHERE id = 1")
    suspend fun getPoints(): Int
    @Query("UPDATE users SET lives = lives + 1, totalPoints = totalPoints - 200 WHERE id = 1")
    suspend fun buyLife()
    @Query("UPDATE users SET awards = awards + :number WHERE id = 1")
    suspend fun updateUserAwards(number: Int)


}