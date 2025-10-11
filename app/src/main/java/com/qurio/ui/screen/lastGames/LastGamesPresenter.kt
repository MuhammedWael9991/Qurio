package com.qurio.ui.screen.lastGames

import jakarta.inject.Inject

class LastGamesPresenter @Inject constructor(): LastGamesContract.Presenter {

    private var view: LastGamesContract.View? = null

    override fun attachView(view: LastGamesContract.View) {
        this.view = view
    }

    override fun detachView() {
        view  = null
    }

    override fun onClickBack() {
        view?.navigateBack()
    }
}