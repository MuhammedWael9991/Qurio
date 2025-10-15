package com.qurio.data.repositoryImpl

import android.util.Log
import com.qurio.data.remote.api.TriviaApi
import com.qurio.data.remote.model.Question
import com.qurio.data.remote.model.QuestionsResponse
import com.qurio.data.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val api: TriviaApi
) : QuestionRepository {

    override suspend fun getQuestion(categoryId: Int, amount: Int, difficulty: String): List<Question> {
        val response = api.getQuestions(
            category = categoryId,
            amount = amount,
            difficulty = difficulty
        )
        Log.d("response", "repoImpl: response = $response")
        return response.results
    }
    
}