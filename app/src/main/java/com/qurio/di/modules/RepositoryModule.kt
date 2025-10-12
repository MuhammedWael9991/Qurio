package com.qurio.di.modules

import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.dao.CharactersDao
import com.qurio.data.repository.AchievementsRepository
import com.qurio.data.repository.CharactersRepository
import com.qurio.data.repositoryImpl.AchievementsRepositoryImpl
import com.qurio.data.repositoryImpl.CharactersRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object RepositoryModule {


    @Provides
    @Singleton
    fun provideCharactersRepository(dao: CharactersDao): CharactersRepository {
        return CharactersRepositoryImpl(dao)
    }

    @Provides
    @Singleton
    fun provideAchievementsRepository(dao: AchievementsDao): AchievementsRepository {
        return AchievementsRepositoryImpl(dao)
    }
}
