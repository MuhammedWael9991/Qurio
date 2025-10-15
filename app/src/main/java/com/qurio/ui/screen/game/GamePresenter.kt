package com.qurio.ui.screen.game

import android.util.Log
import com.qurio.data.remote.model.Question
import com.qurio.data.repository.QuestionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GamePresenter @Inject constructor(
    private val questionRepository: QuestionRepository
): GameContract.Presenter {

    private var view: GameContract.View? = null

    private var questions: List<Question> = emptyList()
    private var currentQuestionIndex = 0
    private var score = 0
    private var correct = 0
    private var inCorrect = 0
    private var skipped = 0

    override fun attachView(view: GameContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getGameQuestions(
        categoryId: Int,
        difficulty: String,
        amount: Int
    ) {
        view?.showLoading()
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val result = questionRepository.getQuestion(categoryId, amount, difficulty)

                questions = result
                currentQuestionIndex = 0
                score = 0

                withContext(Dispatchers.Main){
                    view?.hideLoading()
                    showCurrentQuestion()
                }

            } catch (e: Exception) {

                CoroutineScope(Dispatchers.Main).launch {
                    view?.hideLoading()
                    view?.showError(e.message.toString())
                    Log.e("response", e.message.toString())
                }

            }
        }
    }

    private fun showCurrentQuestion(){
        if (currentQuestionIndex < questions.size){
            view?.showQuestion(questions[currentQuestionIndex])
        }else {
            view?.showResult(score, correct, inCorrect, skipped)
        }
    }

    override fun checkAnswer(selectedAnswer: String) {
        val currentQuestion = questions[currentQuestionIndex]
        if (selectedAnswer == currentQuestion.correctAnswer) {
            score++
            correct++
            view?.showCorrectAnswer()
        } else {
            inCorrect++
            view?.showWrongAnswer()
        }
    }

    override fun nextQuestion() {
        if (currentQuestionIndex + 1 < questions.size) {
            currentQuestionIndex++
            showCurrentQuestion()
        } else {
            view?.showResult(score, correct, inCorrect, skipped)
        }
    }

    override fun skipQuestion() {
        skipped++
        nextQuestion()
    }

}