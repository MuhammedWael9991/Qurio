package com.qurio.ui.screen.character

import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface CharacterContract {

    interface View : BaseView {

    }

    interface Presenter : BasePresenter<View> {

    }

}