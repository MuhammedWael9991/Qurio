package com.qurio.data.repository

interface UserRepository {

    suspend fun initializeUserData()

}