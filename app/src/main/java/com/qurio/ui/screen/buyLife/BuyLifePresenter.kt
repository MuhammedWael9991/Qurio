package com.qurio.ui.screen.buyLife

import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BuyLifePresenter @Inject constructor(
    private val userRepository: UserRepository
): BuyLifeContract.Presenter {
    private var view: BuyLifeContract.View? = null

    override fun attachView(view: BuyLifeContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun buyLife() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                userRepository.buyLife()
                view?.closeDialog()
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }
        }
    }

    override fun getUserPoints() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val userPoints = userRepository.getPoints()
                view?.updateUi(userPoints)
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }
        }
    }
}