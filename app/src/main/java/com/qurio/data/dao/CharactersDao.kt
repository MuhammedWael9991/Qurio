package com.qurio.data.dao

import androidx.room.Dao
import androidx.room.Query

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters")
    fun getUnlockedCharacters(): Int

    @Query("UPDATE characters SET isOwned = 1 WHERE id = :characterId")
    fun unlockCharacter(characterId: Int)

    @Query("SELECT isOwned FROM characters WHERE id = :characterId")
    fun isCharacterUnlocked(characterId: Int): Boolean

    @Query("SELECT * FROM characters WHERE id = :characterId")
    fun getCharacterDetails(characterId: Int): String
}