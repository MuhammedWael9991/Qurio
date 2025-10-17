package com.qurio.ui.screen.buyLife

import android.os.Bundle
import android.view.View
import com.qurio.QurioApp
import com.qurio.databinding.DialogBuyLifeBinding
import com.qurio.ui.base.BaseDialogFragment
import jakarta.inject.Inject

class BuyLifeFragment: BaseDialogFragment<DialogBuyLifeBinding>(DialogBuyLifeBinding::inflate), BuyLifeContract.View {

    @Inject
    lateinit var presenter: BuyLifeContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getUserPoints()
        setupListener()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
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

    private fun setupListener() {
        binding.buy.setOnClickListener {
            presenter.buyLife()
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }

    override fun closeDialog() {
        dismiss()
    }

    override fun updateUi(userPoint: Int) {
        if (userPoint >= 200) {
            binding.buy.isEnabled = true
        } else {
            binding.buy.isEnabled = false
            binding.buy.background = requireContext().getDrawable(com.qurio.R.drawable.primary_button_disabled_bg)
        }
    }

}