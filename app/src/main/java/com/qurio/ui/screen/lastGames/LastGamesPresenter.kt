package com.qurio.ui.screen.lastGames

import com.qurio.data.repository.LastGamesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LastGamesPresenter @Inject constructor(
    private val lastGamesRepository: LastGamesRepository
): LastGamesContract.Presenter {

    private var view: LastGamesContract.View? = null

    override fun attachView(view: LastGamesContract.View) {
        this.view = view
    }

    override fun detachView() {
        view  = null
    }

    override fun onClickBack() {
        view?.navigateBack()
    }

    override fun loadLastGames() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val lastGames = lastGamesRepository.getLastGames()
                view?.showLastGames(lastGames)
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }
}