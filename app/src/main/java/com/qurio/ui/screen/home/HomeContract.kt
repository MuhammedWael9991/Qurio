package com.qurio.ui.screen.home

import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.data.local.entity.UserEntity
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface HomeContract {
    interface View : BaseView {
        fun navigateToShowAllGames()
        fun navigateToShowAllLastGames()
        fun navigateToDifficultyLevel(category: Int)
        fun displayUserData(user: UserEntity)
        fun displayLastFiveGames(lastGames: List<LastGamesEntity>)
        fun navigateToCharacter()
        fun navigateToAchievements()
        fun navigateToBuyLives()

        fun navigateToSettings()
    }

    interface Presenter : BasePresenter<View> {
        fun getUserData()
        fun onClickShowAllGames()
        fun onClickShowAllLastGames()
        fun onSelectGame(category: Int)
        fun getLastFiveGames()
        fun showSettingsDialog()
        fun showCharacterDialog()
        fun showAchievementDialog()
        fun showBuyLivesDialog()
    }
}