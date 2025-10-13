package com.qurio.ui.screen.settings

import jakarta.inject.Inject

class SettingsPresenter @Inject constructor(): SettingsContract.Presenter {

    private var view: SettingsContract.View? = null

    override fun attachView(view: SettingsContract.View) {
        this.view  = view
    }

    override fun detachView() {
        this.view = null
    }

}