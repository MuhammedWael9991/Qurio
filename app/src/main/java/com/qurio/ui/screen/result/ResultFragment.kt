package com.qurio.ui.screen.result

import android.os.Bundle
import androidx.navigation.fragment.navArgs
import com.qurio.QurioApp
import com.qurio.databinding.FragmentResultBinding
import com.qurio.ui.base.BaseFragment
import javax.inject.Inject

class ResultFragment: BaseFragment<FragmentResultBinding>(FragmentResultBinding::inflate), ResultContract.View {

    @Inject
    lateinit var presenter: ResultContract.Presenter

    private val args: ResultFragmentArgs by navArgs()

    override fun onViewCreated(view: android.view.View, savedInstanceState: android.os.Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        displayResults()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.detachView()
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

    fun displayResults() {
        binding.resultHolder.correctNumber.text = args.correct.toString()
        binding.resultHolder.incorrectNumber.text = args.inCorrect.toString()
        binding.resultHolder.skippedNumber.text = args.skipped.toString()
        binding.resultHolder.pointNumber.text = args.score.toString()
    }


}