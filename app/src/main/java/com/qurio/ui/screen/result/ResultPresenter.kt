package com.qurio.ui.screen.result

import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultPresenter @Inject constructor(
    private val userRepository: UserRepository
): ResultContract.Presenter {

    private var view: ResultContract.View? = null

    override fun attachView(view: ResultContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun storeUserPoints(points: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            userRepository.updateUserPoints(points)
        }
    }
}