package com.qurio.ui.screen.games

import android.os.Bundle
import androidx.navigation.fragment.findNavController
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
        setupListeners()

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

    override fun navigateToGame(categoryId: Int) {
        val action = GamesFragmentDirections.gamesFragmentToDialogDifficultyLevel(categoryId)
        findNavController().navigate(action)
    }

    private fun setupListeners() {
        binding.foodCard.setOnClickListener { presenter.onClickGame(categoryId = 0) }
        binding.filmAndTvCard.setOnClickListener { presenter.onClickGame(categoryId = 11) }
        binding.artsLiteratureCard.setOnClickListener { presenter.onClickGame(categoryId = 25) }
        binding.generalKnowledgeCard.setOnClickListener { presenter.onClickGame(categoryId = 9) }
        binding.geographyCard.setOnClickListener { presenter.onClickGame(categoryId = 22) }
        binding.historyCard.setOnClickListener { presenter.onClickGame(categoryId = 23) }
        binding.musicCard.setOnClickListener { presenter.onClickGame(categoryId = 12) }
        binding.scienceCard.setOnClickListener { presenter.onClickGame(categoryId = 17) }
        binding.societyAndCultureCard.setOnClickListener { presenter.onClickGame(categoryId = 24) }
        binding.sportAndLeisureCard.setOnClickListener { presenter.onClickGame(categoryId = 24) }
        binding.topBar.root.setOnClickListener { findNavController().popBackStack() }

    }

}