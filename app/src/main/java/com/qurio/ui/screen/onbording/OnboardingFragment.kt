package com.qurio.ui.screen.onbording

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.databinding.FragmentOnbordingBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject

class OnboardingFragment
    : BaseFragment<FragmentOnbordingBinding>(FragmentOnbordingBinding::inflate),
    OnboardingContract.View {

    @Inject
    lateinit var presenter: OnboardingContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.attachView(this)

        binding.nextIndicator.setOnClickListener {
            presenter.onNextClicked()
        }

        binding.previousIndicator.setOnClickListener {
            presenter.onPreviousClicked()
        }

        binding.swapUpButton.setOnSwapCompleteListener {
            Toast.makeText(context, "Swap up completed!", Toast.LENGTH_SHORT).show()
        }

        presenter.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
    }

    override fun showPage(page: OnboardingPage, forward: Boolean) {
        val animOut = AnimationUtils.loadAnimation(
            requireContext(),
            if (forward) R.anim.slide_out_left else R.anim.slide_out_right
        )
        val animIn = AnimationUtils.loadAnimation(
            requireContext(),
            if (forward) R.anim.slide_in_right else R.anim.slide_in_left
        )

        binding.titleLarge.startAnimation(animOut)
        binding.textContainer.startAnimation(animOut)
        binding.image.startAnimation(animOut)

        animOut.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                binding.titleLarge.text = page.title
                binding.title.text = page.title
                binding.description.text = page.description
                binding.image.setImageResource(page.imageRes)

                binding.titleLarge.startAnimation(animIn)
                binding.textContainer.startAnimation(animIn)
                binding.image.startAnimation(animIn)
            }
            override fun onAnimationRepeat(animation: Animation?) {}
            override fun onAnimationStart(animation: Animation?) {}
        })
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



data class OnboardingPage(
    val title: String,
    val description: String,
    val imageRes: Int
)
