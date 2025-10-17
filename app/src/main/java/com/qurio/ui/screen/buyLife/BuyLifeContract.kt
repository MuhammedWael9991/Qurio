package com.qurio.ui.screen.buyLife

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface BuyLifeContract {

    interface View: BaseView {
        fun closeDialog()
        fun updateUi(userPoint: Int)
    }

    interface Presenter: BasePresenter<View> {
        fun buyLife()
        fun getUserPoints()
    }

}