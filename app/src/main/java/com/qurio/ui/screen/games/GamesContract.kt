package com.qurio.ui.screen.games

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface GamesContract {
    interface View : BaseView {
        fun navigateToGame(categoryId: Int)
    }

    interface Presenter : BasePresenter<View> {
        fun onClickGame(categoryId: Int)
    }
}