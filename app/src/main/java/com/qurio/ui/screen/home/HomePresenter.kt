package com.qurio.ui.screen.home

import android.util.Log
import com.qurio.data.repository.LastGamesRepository
import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val lastGamesRepository: LastGamesRepository
) : HomeContract.Presenter {

    private var view: HomeContract.View? = null


    override fun attachView(view: HomeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getUserData() {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.getUserData()
            view?.displayUserData(user)
        }
    }

    override fun onClickShowAllGames() {
        view?.navigateToShowAllGames()
    }

    override fun onClickShowAllLastGames() {
        view?.navigateToShowAllLastGames()
    }

    override fun onSelectGame(category: Int) {
        view?.navigateToDifficultyLevel(category)
    }

    override fun getLastFiveGames() {
        CoroutineScope(Dispatchers.IO).launch {
            val lastGames = lastGamesRepository.getLastFiveGames()
            Log.d("lastGames", "lastGames = $lastGames")
            view?.displayLastFiveGames(lastGames)
        }
    }

    override fun showSettingsDialog() {
        view?.navigateToSettings()
    }

    override fun showCharacterDialog() {
        view?.navigateToCharacter()
    }

    override fun showAchievementDialog() {
        view?.navigateToAchievements()
    }

    override fun showBuyLivesDialog() {
        view?.navigateToBuyLives()
    }
}