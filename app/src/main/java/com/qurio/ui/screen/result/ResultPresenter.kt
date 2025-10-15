package com.qurio.ui.screen.result

import jakarta.inject.Inject

class ResultPresenter @Inject constructor(): ResultContract.Presenter {

    private var view: ResultContract.View? = null

    override fun attachView(view: ResultContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}