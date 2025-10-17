package com.qurio.ui.screen.achievements

import jakarta.inject.Inject

class AchievementPresenter @Inject constructor(): AchievementContract.Presenter {
    private var view: AchievementContract.View? = null

    override fun attachView(view: AchievementContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}