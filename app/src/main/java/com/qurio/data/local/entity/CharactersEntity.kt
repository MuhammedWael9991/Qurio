package com.qurio.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "characters")
data class CharactersEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val image: String,
    val lockedImage: String,
    val price: Int,
    val age: String,
    val isOwned: Boolean,
    val isSelected: Boolean,
    val description: String,
)