package com.qurio.di.modules

import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.dao.CharactersDao
import com.qurio.data.local.dao.UserDao
import com.qurio.data.remote.api.TriviaApi
import com.qurio.data.repository.AchievementsRepository
import com.qurio.data.repository.CharactersRepository
import com.qurio.data.repository.QuestionRepository
import com.qurio.data.repository.UserRepository
import com.qurio.data.repositoryImpl.AchievementsRepositoryImpl
import com.qurio.data.repositoryImpl.CharactersRepositoryImpl
import com.qurio.data.repositoryImpl.QuestionRepositoryImpl
import com.qurio.data.repositoryImpl.UserRepositoryImpl
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

    @Provides
    @Singleton
    fun provideQuestionRepository(api: TriviaApi): QuestionRepository{
        return QuestionRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUserRepository(dao: UserDao): UserRepository {
        return UserRepositoryImpl(dao)
    }
}
