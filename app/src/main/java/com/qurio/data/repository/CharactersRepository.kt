package com.qurio.data.repository

import com.qurio.data.local.entity.CharactersEntity

interface CharactersRepository {

    suspend fun initCharacters()

    suspend fun getAllCharacters(): List<CharactersEntity>

    suspend fun buyCharacter(characterId: Int)

    suspend fun getSelectedCharacter() : CharactersEntity

    suspend fun selectCharacter(characterId: Int)

}