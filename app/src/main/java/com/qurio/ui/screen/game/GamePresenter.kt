package com.qurio.ui.screen.game

import android.util.Log
import com.qurio.data.repository.QuestionRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GamePresenter @Inject constructor(
    private val questionRepository: QuestionRepository
): GameContract.Presenter {

    private var view: GameContract.View? = null

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
                val questions = questionRepository.getQuestion(categoryId = categoryId, amount = amount, difficulty = difficulty)
                CoroutineScope(Dispatchers.Main).launch {
                    view?.hideLoading()
                    view?.showQuestions(questions)
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
}