package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.qurio.data.local.entity.CharactersEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharactersEntity>)

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun getCount(): Int
    @Query("SELECT * FROM characters")
    suspend fun getAllCharacters(): List<CharactersEntity>
    @Query("UPDATE characters SET isOwned = 1 WHERE id = :characterId")
    suspend fun buyCharacter(characterId: Int)
}