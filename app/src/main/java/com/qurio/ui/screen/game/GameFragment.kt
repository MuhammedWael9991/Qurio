package com.qurio.ui.screen.game

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.data.remote.model.Question
import com.qurio.databinding.FragmentGameBinding
import com.qurio.ui.base.BaseFragment
import jakarta.inject.Inject

class GameFragment: BaseFragment<FragmentGameBinding>(FragmentGameBinding::inflate), GameContract.View {

    @Inject
    lateinit var presenter: GameContract.Presenter

    private val args: GameFragmentArgs by navArgs()

    private var selectedAnswer = ""
    private var selectedOption: View? = null



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        Log.d("args", "args= ${args.category}, ${args.difficulty}, ${args.amount}")
        presenter.getGameQuestions(args.category, args.difficulty, args.amount)
        initClickListeners()
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

    override fun showQuestion(question: Question) {
        binding.questionHolder.questionText.text = question.question
        val answers = question.incorrectAnswers + question.correctAnswer
        val shuffledAnswers = answers.shuffled()
        binding.questionAnswers.answerText1.text = shuffledAnswers[0]
        binding.questionAnswers.answerText2.text = shuffledAnswers[1]
        binding.questionAnswers.answerText3.text = shuffledAnswers[2]
        binding.questionAnswers.answerText4.text = shuffledAnswers[3]
    }

    override fun showCorrectAnswer() {
        selectedOption?.setBackgroundResource(R.drawable.answer_option_true)
    }

    override fun showWrongAnswer() {
        selectedOption?.setBackgroundResource(R.drawable.answer_option_false)
    }

    override fun showResult(score: Int, correct: Int, inCorrect: Int, skipped: Int) {
        val action = GameFragmentDirections.gameFragmentToResultFragment(
            score = score,
            correct = correct,
            inCorrect = inCorrect,
            skipped = skipped
        )
        findNavController().navigate(action)
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

    fun initClickListeners() {

        binding.questionAnswers.apply {
            answerOption1.setOnClickListener {
                selectAnswer(answerOption1, answerText1.text.toString())
            }
            answerOption2.setOnClickListener {
                selectAnswer(answerOption2, answerText2.text.toString())
            }
            answerOption3.setOnClickListener {
                selectAnswer(answerOption3, answerText3.text.toString())
            }
            answerOption4.setOnClickListener {
                selectAnswer(answerOption4, answerText4.text.toString())
            }
        }

        binding.checkAnswersButton.setOnClickListener {
            Log.d("checkAnswer", "selectedAnswer= $selectedAnswer")
            presenter.checkAnswer(selectedAnswer)
            binding.skipAnswersButton.visibility = View.GONE
            binding.checkAnswersButton.visibility = View.GONE
            binding.nextButtonButton.visibility = View.VISIBLE
            setOptionsEnabled(false)
        }

        binding.nextButtonButton.setOnClickListener {
            presenter.nextQuestion()
            binding.skipAnswersButton.visibility = View.VISIBLE
            binding.checkAnswersButton.visibility = View.VISIBLE
            binding.nextButtonButton.visibility = View.GONE
            setOptionsEnabled(true)
            resetOptionsBackground()
            selectedOption = null
            selectedAnswer = ""
        }

        binding.skipAnswersButton.setOnClickListener {
            presenter.skipQuestion()
        }
    }

    private fun selectAnswer(selectedView: View, selectedText: String) {
        selectedAnswer = selectedText
        selectedOption = selectedView

        val options = listOf(
            binding.questionAnswers.answerOption1,
            binding.questionAnswers.answerOption2,
            binding.questionAnswers.answerOption3,
            binding.questionAnswers.answerOption4
        )

        options.forEach { it.setBackgroundResource(R.drawable.button_outlined_disabled) }

        selectedView.setBackgroundResource(R.drawable.answer_option_selected)
    }

    private fun setOptionsEnabled(enabled: Boolean){
        val options = listOf(
            binding.questionAnswers.answerOption1,
            binding.questionAnswers.answerOption2,
            binding.questionAnswers.answerOption3,
            binding.questionAnswers.answerOption4
        )

        options.forEach {
            it.isEnabled = enabled
            it.isClickable = enabled
        }
    }

    private fun resetOptionsBackground(){
        val options = listOf(
            binding.questionAnswers.answerOption1,
            binding.questionAnswers.answerOption2,
            binding.questionAnswers.answerOption3,
            binding.questionAnswers.answerOption4
        )

        options.forEach { it.setBackgroundResource(R.drawable.button_outlined_disabled) }
    }

}