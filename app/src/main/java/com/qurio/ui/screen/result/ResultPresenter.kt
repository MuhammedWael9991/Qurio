package com.qurio.ui.screen.result

import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.data.repository.LastGamesRepository
import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultPresenter @Inject constructor(
    private val userRepository: UserRepository,
    private val lastGamesRepository: LastGamesRepository
): ResultContract.Presenter {

    private var view: ResultContract.View? = null

    override fun attachView(view: ResultContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun storeGameResult(category: String, point: Int, stars: Int, duration: String, data: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                lastGamesRepository.insertLastGame(
                    LastGamesEntity(
                        id = 0,
                        category = category,
                        points = point,
                        stars = stars,
                        duration = duration,
                        date = data
                    )
                )
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }

    override fun storeUserPoints(points: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userRepository.updateUserPoints(points)
            }
            catch (e: Exception) {
                view?.showError(e.message.toString())
            }
        }
    }
}