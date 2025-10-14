package com.qurio.ui.screen.game

import com.qurio.databinding.FragmentGameBinding
import com.qurio.ui.base.BaseFragment

class GameFragment: BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate), GameContract.View {

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