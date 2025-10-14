package com.qurio.data.remote.api

import com.qurio.data.remote.model.QuestionsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaApi {

    @GET("api.php")
    suspend fun getQuestions(
        @Query("category") category: Int,
        @Query("amount") amount: Int,
        @Query("difficulty") difficulty: String,
        @Query("type") type: String = "multiple"
    ): QuestionsResponse
}