package com.qurio.ui.screen.achievements

import com.qurio.data.local.entity.AchievementsEntity
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface AchievementContract {

    interface View: BaseView {
        fun displayAchievements(achievements: List<AchievementsEntity>)
        fun showDetails(achievement: AchievementsEntity)
    }

    interface Presenter: BasePresenter<View> {
        fun loadAchievements()
        fun goToDetails(achievement: AchievementsEntity)
    }

}