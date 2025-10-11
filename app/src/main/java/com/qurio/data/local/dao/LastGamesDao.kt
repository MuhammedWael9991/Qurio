package com.qurio.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qurio.data.local.entity.LastGamesEntity

@Dao
interface LastGamesDao {

    @Query("SELECT * FROM last_games ORDER BY date DESC LIMIT 10")
    suspend fun getLastGames(): List<LastGamesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLastGame(lastGame: LastGamesEntity)
}