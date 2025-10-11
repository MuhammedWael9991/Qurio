package com.qurio.ui.screen.home

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface HomeContract {
    interface View : BaseView {
        fun navigateToShowAllGames()
        fun navigateToShowAllLastGames()
    }

    interface Presenter : BasePresenter<View> {
        fun onClickShowAllGames()
        fun onClickShowAllLastGames()
    }
}