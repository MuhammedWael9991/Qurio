package com.qurio.data.repository

import com.qurio.data.remote.model.QuestionsResponse

interface QuestionRepository {
    suspend fun getQuestion(categoryId: Int, amount: Int, difficulty: String): QuestionsResponse
}