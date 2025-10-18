package com.qurio.ui.screen.settings

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qurio.QurioApp
import com.qurio.databinding.DialogSettingsBinding
import com.qurio.ui.base.BaseDialogFragment
import jakarta.inject.Inject

class SettingsFragment: BaseDialogFragment<DialogSettingsBinding>(DialogSettingsBinding::inflate), SettingsContract.View {

    @Inject
    lateinit var presenter: SettingsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        setupListeners()
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

    fun setupListeners() {
        binding.discardButton.setOnClickListener {
            dismiss()
        }
        binding.saveButton.setOnClickListener {
            Toast.makeText(requireContext(), "Soon!", Toast.LENGTH_SHORT).show()
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
    }

}
