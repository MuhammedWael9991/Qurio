package com.qurio.data.repositoryImpl

import com.qurio.data.local.dao.AchievementsDao
import com.qurio.data.local.dao.UserDao
import com.qurio.data.local.entity.UserEntity
import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao,
    private val achievementDao: AchievementsDao
): UserRepository {

    override suspend fun initializeUserData() {
        if (dao.getCount() == 0){
            dao.insertUser(
                user = UserEntity(
                    id = 1,
                    characterId = 1,
                    totalPoints = 0,
                    lives = 3,
                    awards = 0,
                    streak = 0
                )
            )
        }
    }

    override suspend fun getUserData(): UserEntity {
        return dao.getUser()
    }

    override suspend fun updateUserPoints(point: Int) {
        dao.updateUserPoints(point)
    }

    override suspend fun getPoints(): Int {
        return dao.getPoints()
    }

    override suspend fun buyLife() {
        dao.buyLife()
    }

    override suspend fun updateUserAwards(number: Int) {
        val isAchievementUnlocked = achievementDao.isUnlocked(number)
        if (isAchievementUnlocked) return
        dao.updateUserAwards(number)
    }
}