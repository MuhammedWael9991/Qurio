package com.qurio.data.repositoryImpl

import android.util.Log
import com.qurio.data.remote.api.TriviaApi
import com.qurio.data.remote.model.QuestionsResponse
import com.qurio.data.repository.QuestionRepository

class QuestionRepositoryImpl(
    private val api: TriviaApi
) : QuestionRepository {
    
    override suspend fun getQuestion(): QuestionsResponse {
        val response = api.getQuestions(
            category = 21,
            amount = 3,
            difficulty = "easy"
        )
        Log.d("response", "response = $response")
        return response
    }
    
}