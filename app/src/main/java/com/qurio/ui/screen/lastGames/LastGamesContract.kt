package com.qurio.ui.screen.lastGames

import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface LastGamesContract {
    interface View: BaseView {
        fun navigateBack()
        fun showLastGames(lastGames: List<LastGamesEntity>)
    }
    interface Presenter: BasePresenter<View> {
        fun onClickBack()
        fun loadLastGames()
    }
}