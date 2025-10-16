package com.qurio.di.modules

import android.app.Application
import androidx.room.Room
import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.dao.CharactersDao
import com.qurio.data.local.dao.LastGamesDao
import com.qurio.data.local.dao.UserDao
import com.qurio.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase =
        Room.databaseBuilder(app, AppDatabase::class.java, "qurio_db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideUserDao(db: AppDatabase): UserDao = db.userDao()

    @Provides
    fun provideCharactersDao(db: AppDatabase): CharactersDao {
        return db.charactersDao()
    }

    @Provides
    fun provideAchievementsDao(db: AppDatabase): AchievementsDao {
        return db.achievementsDao()
    }

    @Provides
    fun provideLastGamesDao(db: AppDatabase): LastGamesDao {
        return db.lastGamesDao()
    }
}
