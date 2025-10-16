package com.qurio.ui.screen.game

import com.qurio.data.remote.model.Question
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface GameContract {

    interface View: BaseView {
        fun showQuestion(question: Question)
        fun showCorrectAnswer()
        fun showWrongAnswer()
        fun showResult(score: Int, correct: Int, inCorrect: Int, skipped: Int, duration: Long)

        fun getQuestionTime(): Int
        fun updateTimer(secondsPassed: Int, totalSeconds: Int)
        fun showTimeUp()
        fun updateCurrentQuestionNumber(current: Int, total: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun getGameQuestions(categoryId: Int , difficulty: String , amount: Int = 1)
        fun checkAnswer(selectedAnswer: String)
        fun nextQuestion()
        fun skipQuestion()

        fun startTimer(totalSeconds: Int)
        fun stopTimer()
        fun updateQuestionNumber()
    }
}