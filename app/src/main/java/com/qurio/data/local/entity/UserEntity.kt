package com.qurio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: Int = 1,
    val characterId: Int,
    val totalPoints: Int,
    val lives: Int,
    val awards: Int,
    val streak:Int
)
