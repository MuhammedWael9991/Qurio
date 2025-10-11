package com.qurio.ui.screen.games

import jakarta.inject.Inject

class GamesPresenter @Inject constructor(): GamesContract.Presenter {

    private var view: GamesContract.View? = null

    override fun attachView(view: GamesContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}