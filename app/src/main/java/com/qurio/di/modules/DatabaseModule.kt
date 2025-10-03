package com.qurio.di.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object DatabaseModule {

//    @Provides
//    @Singleton
//    fun provideDatabase(app: Application): AppDatabase {
//        return Room.databaseBuilder(app, AppDatabase::class.java, "app_db")
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    fun provideQuestionDao(db: AppDatabase): QuestionDao = db.questionDao()
}
