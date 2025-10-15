package com.qurio.ui.screen.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.navArgs
import com.qurio.QurioApp
import com.qurio.data.remote.model.QuestionsResponse
import com.qurio.databinding.FragmentGameBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject

class GameFragment: BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate), GameContract.View {

    @Inject
    lateinit var presenter: GameContract.Presenter

    private val args: GameFragmentArgs by navArgs()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        Log.d("args", "args= ${args.category}, ${args.difficulty}, ${args.amount}")
        presenter.getGameQuestions(args.category, args.difficulty, args.amount)
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
        hideViews()
        binding.loadingView.root.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        showViews()
        binding.loadingView.root.visibility = View.GONE
    }

    override fun showError(message: String) {
        TODO("Not yet implemented")
    }

    override fun showQuestions(questions: QuestionsResponse) {
        if (questions.results.isNotEmpty()) {
            binding.questionHolder.questionText.text = questions.results[0].question
            binding.questionAnswers.answerText1.text = questions.results[0].correctAnswer
            binding.questionAnswers.answerText2.text = questions.results[0].incorrectAnswers[0]
            binding.questionAnswers.answerText3.text = questions.results[0].incorrectAnswers[1]
            binding.questionAnswers.answerText4.text = questions.results[0].incorrectAnswers[2]
        }
    }

    fun hideViews() {
        binding.questionHolder.root.visibility = View.GONE
        binding.topBar.visibility = View.GONE
        binding.questionAnswers.root.visibility = View.GONE
        binding.checkAnswersButton.visibility = View.GONE
        binding.skipAnswersButton.visibility = View.GONE
    }

    fun showViews() {
        binding.questionHolder.root.visibility = View.VISIBLE
        binding.topBar.visibility = View.VISIBLE
        binding.questionAnswers.root.visibility = View.VISIBLE
        binding.checkAnswersButton.visibility = View.VISIBLE
        binding.skipAnswersButton.visibility = View.VISIBLE
    }

}