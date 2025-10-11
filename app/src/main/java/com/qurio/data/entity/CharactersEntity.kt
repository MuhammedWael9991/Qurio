package com.qurio.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: Int,
    val price: Int,
    val isOwned: Boolean,
    val description: String,
)