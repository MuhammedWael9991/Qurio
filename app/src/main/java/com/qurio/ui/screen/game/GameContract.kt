package com.qurio.ui.screen.game

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface GameContract {

    interface View: BaseView {

    }

    interface Presenter: BasePresenter<View> {

    }
}