package com.qurio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_games")
data class LastGamesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val points: Int,
    val stars: Int,
    val duration: String,
    val date: String
)
