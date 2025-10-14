package com.qurio.ui.screen.game

import jakarta.inject.Inject

class GamePresenter @Inject constructor(): GameContract.Presenter {

    private var view: GameContract.View? = null

    override fun attachView(view: GameContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}