package com.qurio.ui.screen.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.data.local.entity.CharactersEntity
import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.data.local.entity.UserEntity
import com.qurio.databinding.FragmentHomeBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject
import kotlin.math.abs

class HomeFragment() : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter
    private val lastGamesAdapter = LastGamesAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)
        presenter.showCharacterData()
        displayGames()
        setupListeners()
        setupRecycler()
        presenter.getUserData()
        presenter.getLastFiveGames()

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

    override fun navigateToDifficultyLevel(category: Int) {
        val action = HomeFragmentDirections.homeFragmentToDialogDifficultyLevel(category)
        findNavController().navigate(action)
    }

    override fun displayUserData(user: UserEntity) {
        binding.statistics.lives.text = user.lives.toString()
        binding.statistics.points.text = user.totalPoints.toString()
        binding.statistics.awards.text = user.awards.toString()
        if (user.totalPoints >= 1000) {
            binding.statistics.eliteBadge.visibility = View.VISIBLE
        }else {
            binding.statistics.eliteBadge.visibility = View.GONE
        }
    }

    private fun setupRecycler() = with(binding.lastGamesRecyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = lastGamesAdapter
        visibility = View.GONE
        isNestedScrollingEnabled = false
    }

    override fun displayLastFiveGames(lastGames: List<LastGamesEntity>) {
        if (lastGames.isEmpty()) {
            binding.titleLastGames.visibility = View.GONE
            binding.lastGamesRecyclerView.visibility = View.GONE
        } else {
            binding.titleLastGames.visibility = View.VISIBLE
            binding.lastGamesRecyclerView.visibility = View.VISIBLE
            lastGamesAdapter.setData(lastGames)
        }
    }

    override fun characterInfo(character: CharactersEntity) {
        binding.topBar.topBarPrimary.visibility = View.VISIBLE
        binding.topBar.topBarSecondary.visibility = View.GONE
        binding.topBar.avatarName.text = character.name
        val resId = requireContext().resources.getIdentifier(character.image, "drawable", requireContext().packageName)
        binding.topBar.avatarImage.setImageResource(resId)
    }

    override fun navigateToCharacter() {
        findNavController().navigate(R.id.homeFragment_to_DialogCharacterFragment)
    }

    override fun navigateToAchievements() {
        findNavController().navigate(R.id.homeFragment_to_DialogAchievementFragment)
    }

    override fun navigateToBuyLives() {
        findNavController().navigate(R.id.homeFragment_to_dialogBuyLife)
    }

    override fun navigateToSettings() {
        findNavController().navigate(R.id.homeFragment_to_DialogSettingsFragment)
    }

    fun displayGames(){
        val gamePage = listOf(
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
    }
    fun setupListeners(){
        binding.allGamesButton.setOnClickListener {
            presenter.onClickShowAllGames()
        }
        binding.lastGamesButton.setOnClickListener {
            presenter.onClickShowAllLastGames()
        }

        binding.topBar.settingButton.setOnClickListener {
            presenter.showSettingsDialog()

        }

        binding.topBar.avatarImage.setOnClickListener {
            presenter.showCharacterDialog()

        }

        binding.statistics.awardsHolder.setOnClickListener {
            presenter.showAchievementDialog()
        }
        binding.statistics.livesHolder.setOnClickListener {
            presenter.showBuyLivesDialog()
        }
    }

}