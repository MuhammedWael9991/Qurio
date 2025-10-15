package com.qurio.ui.screen.onbording

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface OnboardingContract {
    interface View : BaseView {
        fun showPage(page: OnboardingPage, forward: Boolean)
        fun navigateToHome()
        fun markOnboardingAsCompleted()
    }

    interface Presenter : BasePresenter<View> {
        fun onNextClicked()
        fun onPreviousClicked()
        fun onSwapUp()
        fun start()
    }
}