package com.qurio.ui.screen.result

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface ResultContract {
    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {
        fun storeUserPoints(points: Int)
    }
}