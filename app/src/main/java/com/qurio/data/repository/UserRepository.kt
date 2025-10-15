package com.qurio.data.repository

import com.qurio.data.local.entity.UserEntity

interface UserRepository {

    suspend fun initializeUserData()
    suspend fun getUserData(): UserEntity
    suspend fun updateUserPoints(point: Int)

}