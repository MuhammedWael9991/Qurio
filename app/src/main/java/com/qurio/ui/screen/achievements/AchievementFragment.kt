package com.qurio.ui.screen.achievements

import android.os.Bundle
import android.view.View
import com.qurio.QurioApp
import com.qurio.databinding.DialogAchievementBinding
import com.qurio.ui.base.BaseDialogFragment
import javax.inject.Inject

class AchievementFragment: BaseDialogFragment<DialogAchievementBinding>(DialogAchievementBinding::inflate), AchievementContract.View {


    @Inject
    lateinit var presenter: AchievementContract.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
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

}