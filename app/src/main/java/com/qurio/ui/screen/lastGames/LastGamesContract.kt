package com.qurio.ui.screen.lastGames

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface LastGamesContract {
    interface View: BaseView {
        fun navigateBack()
    }
    interface Presenter: BasePresenter<View> {
        fun onClickBack()
    }
}