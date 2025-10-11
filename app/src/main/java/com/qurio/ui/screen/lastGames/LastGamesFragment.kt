package com.qurio.ui.screen.lastGames

import android.os.Bundle
import com.qurio.QurioApp
import com.qurio.databinding.FragmentLastGamesBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject

class LastGamesFragment: BaseFragment<FragmentLastGamesBinding>(FragmentLastGamesBinding::inflate), LastGamesContract.View {

    @Inject
    lateinit var presenter: LastGamesContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)

        binding.topBar.setOnClickListener {
            presenter.onClickBack()
        }

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

    override fun navigateBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

}