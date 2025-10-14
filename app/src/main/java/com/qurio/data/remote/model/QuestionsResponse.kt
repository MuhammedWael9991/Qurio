package com.qurio.data.remote.model

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("response_code") val responseCode: Int,
    @SerializedName("results") val results: List<Question>
)

data class Question(
    val type: String,
    val difficulty: String,
    val category: String,
    val question: String,
    @SerializedName("correct_answer") val correctAnswer: String,
    @SerializedName("incorrect_answers") val incorrectAnswers: List<String>
)