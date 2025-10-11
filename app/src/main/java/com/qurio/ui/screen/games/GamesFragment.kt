package com.qurio.ui.screen.games

import android.os.Bundle
import com.qurio.QurioApp
import com.qurio.databinding.FragmentGamesBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject

class GamesFragment() : BaseFragment<FragmentGamesBinding>(FragmentGamesBinding::inflate), GamesContract.View {

    @Inject
    lateinit var presenter: GamesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun hideLoading() {
        TODO("Not yet implemented")
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }
}