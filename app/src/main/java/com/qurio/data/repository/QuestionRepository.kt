package com.qurio.data.repository

interface QuestionRepository {
    suspend fun getQuestion()
}