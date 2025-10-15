package com.qurio.ui.screen.home

import com.qurio.data.local.entity.UserEntity
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface HomeContract {
    interface View : BaseView {
        fun navigateToShowAllGames()
        fun navigateToShowAllLastGames()
        fun navigateToDifficultyLevel(category: Int)
        fun displayUserData(user: UserEntity)
    }

    interface Presenter : BasePresenter<View> {
        fun getUserData()
        fun onClickShowAllGames()
        fun onClickShowAllLastGames()
        fun onSelectGame(category: Int)
    }
}