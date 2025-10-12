package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qurio.data.local.entity.CharactersEntity

@Dao
interface CharactersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<CharactersEntity>)

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun getCount(): Int

    @Query("SELECT * FROM characters")
    suspend fun getUnlockedCharacters(): List<CharactersEntity>

    @Query("UPDATE characters SET isOwned = 1 WHERE id = :characterId")
    suspend fun unlockCharacter(characterId: Int)

    @Query("SELECT isOwned FROM characters WHERE id = :characterId")
    suspend fun isCharacterUnlocked(characterId: Int): Boolean

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterDetails(characterId: Int): List<CharactersEntity>
}