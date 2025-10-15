package com.qurio.ui.screen.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.databinding.FragmentHomeBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject
import kotlin.math.abs

class HomeFragment() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        val gamePage = listOf<GamePage>(
            GamePage(
                imageRes = R.drawable.music_game,
                category = 12
            ),
            GamePage(
                imageRes = R.drawable.food_and_drink_game,
                category = 0
            ),
            GamePage(
                imageRes = R.drawable.geography_game,
                category = 22
            ),
            GamePage(
                imageRes = R.drawable.general_knowledge_game,
                category = 9
            ),
            GamePage(
                imageRes = R.drawable.film_and_tv_game,
                category = 11
            ),
            GamePage(
                imageRes = R.drawable.science_game,
                category = 17
            ),
            GamePage(
                imageRes = R.drawable.society_and_culture_game,
                category = 24
            ),
            GamePage(
                imageRes = R.drawable.sport_and_leisure_game,
                category = 21
            ),
            GamePage(
                imageRes = R.drawable.history_game,
                category = 23
            ),
            GamePage(
                imageRes = R.drawable.arts_literature_game,
                category = 25
            )
        )

        val adapter = GameTypeAdapter(gamePage) { page ->
            Toast.makeText(context, "Click: ${page.category}", Toast.LENGTH_SHORT).show()
            presenter.onSelectGame(page.category)
        }

        binding.gamesViewpager.adapter = adapter
        binding.gamesViewpager.offscreenPageLimit = 3
        (binding.gamesViewpager.getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        binding.gamesViewpager.setPageTransformer { page, position ->
            val scale = 0.85f + (1 - abs(position)) * 0.15f
            page.scaleY = scale
            page.scaleX = scale
        }

        binding.allGamesButton.setOnClickListener {
            presenter.onClickShowAllGames()
        }
        binding.lastGamesButton.setOnClickListener {
            presenter.onClickShowAllLastGames()
        }

        binding.topBar.onSettingsClick = {
            findNavController().navigate(R.id.homeFragment_to_DialogSettingsFragment)
        }

        binding.topBar.onClickAvatar = {
            findNavController().navigate(R.id.homeFragment_to_DialogCharacterFragment)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
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

    override fun navigateToShowAllGames() {
        findNavController().navigate(R.id.homeFragment_to_GamesFragment)
    }

    override fun navigateToShowAllLastGames() {
        findNavController().navigate(R.id.homeFragment_to_LastGamesFragment)
    }

    override fun navigateToGame(category: Int) {
        val action = HomeFragmentDirections.homeFragmentToGameFragment(category)
        findNavController().navigate(action)
    }
}