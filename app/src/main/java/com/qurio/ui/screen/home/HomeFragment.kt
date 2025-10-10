package com.qurio.ui.screen.home

import com.qurio.databinding.FragmentHomeBinding
import com.qurio.ui.base.BaseFragment

class HomeFragment()
    : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    HomeContract.View {
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