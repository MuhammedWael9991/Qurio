package com.qurio.ui.screen.difficultyLevel

import jakarta.inject.Inject

class DifficultyLevelPresenter@Inject constructor() : DifficultyLevelContract.Presenter {
    private var view: DifficultyLevelContract.View? = null

    override fun attachView(view: DifficultyLevelContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}