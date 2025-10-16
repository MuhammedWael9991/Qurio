package com.qurio.data.repositoryImpl

import com.qurio.data.local.dao.LastGamesDao
import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.data.repository.LastGamesRepository
import jakarta.inject.Inject

class LastGamesRepositoryImpl @Inject constructor(
    private val dao: LastGamesDao
): LastGamesRepository {
    override suspend fun getLastGames(): List<LastGamesEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun insertLastGame(lastGame: LastGamesEntity) {
        dao.insertLastGame(lastGame)
    }
}