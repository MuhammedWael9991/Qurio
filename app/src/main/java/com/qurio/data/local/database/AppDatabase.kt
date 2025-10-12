package com.qurio.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.dao.CharactersDao
import com.qurio.data.local.dao.LastGamesDao
import com.qurio.data.local.dao.UserDao
import com.qurio.data.local.entity.AchievementsEntity
import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.data.local.entity.UserEntity
import com.qurio.data.local.entity.CharactersEntity


@Database(
    entities = [
        UserEntity::class,
        AchievementsEntity::class,
        CharactersEntity::class,
        LastGamesEntity::class
    ],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun charactersDao(): CharactersDao
    abstract fun achievementsDao(): AchievementsDao
}
