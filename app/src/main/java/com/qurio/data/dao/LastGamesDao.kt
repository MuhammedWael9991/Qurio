package com.qurio.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.qurio.data.entity.LastGamesEntity

@Dao
interface LastGamesDao {

    @Query("SELECT * FROM last_games ORDER BY date DESC LIMIT 10")
    fun getLastGames(): List<LastGamesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLastGame(lastGame: LastGamesEntity)
}