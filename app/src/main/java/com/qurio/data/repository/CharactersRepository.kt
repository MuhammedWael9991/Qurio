package com.qurio.data.repository

interface CharactersRepository {

    suspend fun getUnlockedCharacters(): Int
    suspend fun unlockCharacter(characterId: Int)
    suspend fun isCharacterUnlocked(characterId: Int): Boolean
    suspend fun getCharacterDetails(characterId: Int): String

}