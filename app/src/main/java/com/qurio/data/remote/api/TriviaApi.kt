package com.qurio.data.remote.api

import com.qurio.data.remote.model.QuestionResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApi {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("category") category: String,
        @Query("amount") amount: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String
    ): List<QuestionResponse>
}