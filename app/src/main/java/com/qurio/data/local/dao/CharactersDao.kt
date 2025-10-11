package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    suspend fun getUnlockedCharacters(): Int

    @Query("UPDATE characters SET isOwned = 1 WHERE id = :characterId")
    suspend fun unlockCharacter(characterId: Int)

    @Query("SELECT isOwned FROM characters WHERE id = :characterId")
    suspend fun isCharacterUnlocked(characterId: Int): Boolean

    @Query("SELECT * FROM characters WHERE id = :characterId")
    suspend fun getCharacterDetails(characterId: Int): String
}