package com.qurio.ui.screen.achievements

import com.qurio.data.local.entity.AchievementsEntity
import com.qurio.data.repository.AchievementsRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AchievementPresenter @Inject constructor(
    private val achievementsRepository: AchievementsRepository
): AchievementContract.Presenter {
    private var view: AchievementContract.View? = null

    override fun attachView(view: AchievementContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun loadAchievements() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val achievements = achievementsRepository.getAchievements()
                view?.displayAchievements(achievements)
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }

    override fun goToDetails(achievement: AchievementsEntity) {
        view?.showDetails(achievement)
    }
}