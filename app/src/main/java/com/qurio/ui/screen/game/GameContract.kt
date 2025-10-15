package com.qurio.ui.screen.game

import com.qurio.data.remote.model.QuestionsResponse
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface GameContract {

    interface View: BaseView {
        fun showQuestions(questions: QuestionsResponse)
    }

    interface Presenter: BasePresenter<View> {
        fun getGameQuestions(categoryId: Int , difficulty: String = "easy" , amount: Int = 1)
    }
}