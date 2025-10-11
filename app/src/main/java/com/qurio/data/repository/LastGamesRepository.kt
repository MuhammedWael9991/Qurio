package com.qurio.data.repository

import com.qurio.data.local.entity.LastGamesEntity

interface LastGamesRepository {
    suspend fun getLastGames(): List<LastGamesEntity>
    suspend fun insertLastGame(lastGame: LastGamesEntity)

}