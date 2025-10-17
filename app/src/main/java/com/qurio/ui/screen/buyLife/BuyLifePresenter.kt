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
            userRepository.buyLife()
            view?.closeDialog()
        }
    }

    override fun getUserPoints() {
        CoroutineScope(Dispatchers.IO).launch {
            val userPoints = userRepository.getPoints()
            view?.updateUi(userPoints)
        }
    }
}