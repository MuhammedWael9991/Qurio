package com.qurio.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.qurio.data.dao.UserDao
import com.qurio.data.entity.AchievementsEntity
import com.qurio.data.entity.LastGamesEntity
import com.qurio.data.entity.UserEntity
import com.qurio.data.entity.CharactersEntity


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
}
