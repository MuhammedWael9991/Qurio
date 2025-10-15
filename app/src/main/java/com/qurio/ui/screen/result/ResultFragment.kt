package com.qurio.ui.screen.result

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.databinding.FragmentResultBinding
import com.qurio.ui.base.BaseFragment
import javax.inject.Inject

class ResultFragment: BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate), ResultContract.View {

    @Inject
    lateinit var presenter: ResultContract.Presenter

    private val args: ResultFragmentArgs by navArgs()

    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        displayResults()
        storeUsePoints()
        initListener()
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

    fun displayResults() {
        binding.resultHolder.correctNumber.text = args.correct.toString()
        binding.resultHolder.incorrectNumber.text = args.inCorrect.toString()
        binding.resultHolder.skippedNumber.text = args.skipped.toString()
        binding.resultHolder.pointNumber.text = args.score.toString()
        if (args.correct == 0) {
            binding.resultHolder.resultHeader.setImageResource(R.drawable.result_headr_lose)
            binding.resultHolder.resultHeaderGlow.setImageResource(R.drawable.result_header_lose_glow)
            binding.resultHolder.resultTitle.text = getString(R.string.you_lose)
            binding.resultHolder.gameStars.oneStar.visibility = View.GONE
            binding.resultHolder.gameStars.twoStars.visibility = View.GONE
            binding.resultHolder.gameStars.threeStars.visibility = View.GONE
        } else {
            showStars(args.correct, args.difficulty)
        }
    }

    fun storeUsePoints() {
        presenter.storeUserPoints(args.score)
    }

    private fun initListener() {

        binding.playAgainButton.setOnClickListener { findNavController().popBackStack() }
        binding.backToHomeButton.setOnClickListener { findNavController().navigate(R.id.resultFragment_to_homeFragment) }

    }

    private fun setStarsVisibility(one: Boolean, two: Boolean, three: Boolean) {
        val stars = binding.resultHolder.gameStars
        stars.oneStar.visibility = if (one) View.VISIBLE else View.GONE
        stars.twoStars.visibility = if (two) View.VISIBLE else View.GONE
        stars.threeStars.visibility = if (three) View.VISIBLE else View.GONE
    }

    private fun showStars(correct: Int, difficulty: String) {
        when (difficulty) {
            "easy" -> {
                when (correct) {
                    1 -> setStarsVisibility(one = true, two = false, three = false)
                    in 2..4 -> setStarsVisibility(one = false, two = true, three = false)
                    else -> setStarsVisibility(one = false, two = false, three = true)
                }
            }

            "medium" -> {
                when (correct) {
                    in 1..5 -> setStarsVisibility(one = true, two = false, three = false)
                    in 6..9 -> setStarsVisibility(one = false, two = true, three = false)
                    else -> setStarsVisibility(one = false, two = false, three = true)
                }
            }

            "hard" -> {
                when (correct) {
                    in 1..10 -> setStarsVisibility(one = true, two = false, three = false)
                    in 11..13 -> setStarsVisibility(one = false, two = true, three = false)
                    else -> setStarsVisibility(one = false, two = false, three = true)
                }
            }
        }
    }

}