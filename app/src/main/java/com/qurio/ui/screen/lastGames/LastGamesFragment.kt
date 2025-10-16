package com.qurio.ui.screen.lastGames

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.qurio.QurioApp
import com.qurio.data.local.entity.LastGamesEntity
import com.qurio.databinding.FragmentLastGamesBinding
import com.qurio.ui.base.BaseFragment
import com.qurio.ui.screen.home.LastGamesAdapter
import jakarta.inject.Inject

class LastGamesFragment: BaseFragment<FragmentLastGamesBinding>(FragmentLastGamesBinding::inflate), LastGamesContract.View {

    @Inject
    lateinit var presenter: LastGamesContract.Presenter

    private val lastGamesAdapter = LastGamesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: android.view.View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        setupRecycler()
        presenter.loadLastGames()
        binding.topBar.setOnClickListener {
            presenter.onClickBack()
        }

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

    override fun navigateBack() {
        findNavController().popBackStack()
    }

    private fun setupRecycler() = with(binding.lastGamesRecyclerView) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = lastGamesAdapter
        visibility = View.GONE
        isNestedScrollingEnabled = false
    }

    override fun showLastGames(lastGames: List<LastGamesEntity>) {
        binding.lastGamesRecyclerView.visibility = View.VISIBLE
        lastGamesAdapter.setData(lastGames)
    }

}