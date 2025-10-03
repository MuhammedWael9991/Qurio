package com.qurio.ui.screen.onbording

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface OnboardingContract {
    interface View : BaseView {
        fun showPage(page: OnboardingPage, forward: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun onNextClicked()
        fun onPreviousClicked()
        fun start()
    }
}