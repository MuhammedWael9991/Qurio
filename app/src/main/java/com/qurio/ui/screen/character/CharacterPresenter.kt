package com.qurio.ui.screen.character

import jakarta.inject.Inject

class CharacterPresenter@Inject constructor() : CharacterContract.Presenter {
    private var view: CharacterContract.View? = null

    override fun attachView(view: CharacterContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }
}