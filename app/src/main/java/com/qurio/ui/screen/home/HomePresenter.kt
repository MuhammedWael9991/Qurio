package com.qurio.ui.screen.home

import jakarta.inject.Inject

class HomePresenter @Inject constructor() : HomeContract.Presenter {

    private var view: HomeContract.View? = null


    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onClickShowAllGames() {
        view?.navigateToShowAllGames()
    }
}