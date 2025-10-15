package com.qurio.ui.screen.onbording

import com.qurio.R
import com.qurio.data.repository.AchievementsRepository
import com.qurio.data.repository.CharactersRepository
import com.qurio.data.repository.QuestionRepository
import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnboardingPresenter @Inject constructor(
    private val charactersRepository: CharactersRepository,
    private val achievementsRepository: AchievementsRepository,
    private val userRepository: UserRepository
) : OnboardingContract.Presenter {

    init {
        initGameData()
    }

    private var view: OnboardingContract.View? = null

    private val pages = listOf(
        OnboardingPage(
            title = "Welcome to Qurio",
            description = "Welcome to the world of Qurio, where questions spark curiosity and prizes await the smartest. Ready to begin the challenge?",
            imageRes = R.drawable.brain
        ),
        OnboardingPage(
            title = "Choose your character",
            description = "Each hero has their own unique style!\n" +
                    "Choose from unique characters and start your adventure in your own style.",
            imageRes = R.drawable.avatar_onbording
        ),
        OnboardingPage(
            title = "Challenge and win",
            description = "Answer quickly, earn points, and share with your friends!\n" +
                    "Each trivia category is a new experience.",
            imageRes = R.drawable.crown_onbording
        ),
        OnboardingPage(
            title = "Collect them all!",
            description = "Unlock characters, earn badges, and climb the leaderboards. Qurio is merciless, but you can handle it.",
            imageRes = R.drawable.cup_onbording
        )
    )

    private var currentIndex = 0

    private fun initGameData(){
        CoroutineScope(Dispatchers.IO).launch {
            charactersRepository.initCharacters()
            achievementsRepository.initAchievements()
            userRepository.initializeUserData()
        }
    }

    override fun attachView(view: OnboardingContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun onNextClicked() {
        if (currentIndex < pages.size - 1) {
            currentIndex++
            view?.showPage(pages[currentIndex], true)
        }
    }

    override fun onPreviousClicked() {
        if (currentIndex > 0) {
            currentIndex--
            view?.showPage(pages[currentIndex], false)
        }
    }

    override fun onSwapUp() {
        view?.navigateToHome()
    }

    override fun start() {
        view?.showPage(pages[currentIndex], true)
    }
}