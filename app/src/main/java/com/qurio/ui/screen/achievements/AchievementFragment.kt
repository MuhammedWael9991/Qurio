package com.qurio.ui.screen.achievements

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.data.local.entity.AchievementsEntity
import com.qurio.databinding.DialogAchievementBinding
import com.qurio.databinding.ItemAchievementBinding
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
        presenter.loadAchievements()
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

    override fun displayAchievements(achievements: List<AchievementsEntity>) {
        binding.achievementList.root.visibility = View.VISIBLE
        binding.okButton.visibility = View.VISIBLE

        val achievementsBinds = listOf(
            binding.achievementList.QuizRookie,
            binding.achievementList.StreakStarter,
            binding.achievementList.LuckyGuess,
            binding.achievementList.Explorer,
            binding.achievementList.TriviaChamp,
            binding.achievementList.Collector,
            binding.achievementList.Legend,
            binding.achievementList.Untouchable,
            binding.achievementList.QuickThinker,
            binding.achievementList.CollectorII,
            binding.achievementList.LuckyGuessII,
        )
        onClickAchievement(achievements)
        achievementsBinds.zip(achievements).forEach { (achievementBind, achievement) ->
            bindAchievementView(achievementBind, achievement)
        }

    }

    override fun showDetails(achievement: AchievementsEntity) {
        hideAll()
        val imageRes = when (achievement.title.lowercase()) {
            "quiz rookie" -> if (achievement.isAchieved) R.drawable.quiz_rookie_owned else R.drawable.quiz_rookie_locked
            "streak starter" -> if (achievement.isAchieved) R.drawable.streak_starter_owned else R.drawable.streak_starter_locked
            "lucky guess" -> if (achievement.isAchieved) R.drawable.lucky_guess_owned else R.drawable.lucky_guess_locked
            "explorer" -> if (achievement.isAchieved) R.drawable.explorer_owned else R.drawable.explorer_locked
            "trivia champ" -> if (achievement.isAchieved) R.drawable.trivia_champ_owned else R.drawable.trivia_champ_locked
            "collector" -> if (achievement.isAchieved) R.drawable.collector_owned else R.drawable.collector_locked
            "legend" -> if (achievement.isAchieved) R.drawable.legend_owned else R.drawable.legend_locked
            "collector ii" -> if (achievement.isAchieved) R.drawable.collector_ii_owned else R.drawable.collector_ii_locked
            "lucky Guess ii" -> if (achievement.isAchieved) R.drawable.lucky_guess_ii_owned else R.drawable.lucky_guess_ii_locked
            else -> if (achievement.isAchieved) R.drawable.quiz_rookie_owned else R.drawable.quiz_rookie_locked
        }
        binding.achievementDetails.root.visibility = View.VISIBLE
        binding.achievementDetails.name.text = achievement.title
        binding.achievementDetails.description.text = achievement.description
        binding.achievementDetails.howToGetDescription.text = achievement.howToGetIt
        binding.achievementDetails.image.setImageResource(imageRes)
        binding.achievementDetails.imageBg.visibility = if (achievement.isAchieved) View.VISIBLE else View.GONE
        binding.headerText.text = achievement.title
        if (achievement.isAchieved) {
            binding.shareOkButtonsLayout.visibility = View.VISIBLE
            binding.shareButton.visibility = View.VISIBLE
            binding.okDetailsOwnButton.visibility = View.VISIBLE
            Toast.makeText(requireContext(), "Achievement unlocked!", Toast.LENGTH_SHORT).show()
        } else {
            binding.okDetailsButton.visibility = View.VISIBLE
        }
    }


    private fun bindAchievementView(
        achievementBinding: ItemAchievementBinding,
        achievement: AchievementsEntity
    ) {
        with(achievementBinding) {
            name.text = achievement.title

            val nameColor = if (achievement.isAchieved) {
                R.color.Shade_Primary
            } else {
                R.color.Shade_Tertiary
            }

            name.setTextColor(resources.getColor(nameColor, null))
            val imageRes = when (achievement.title.lowercase()) {
                "quiz rookie" -> if (achievement.isAchieved) R.drawable.quiz_rookie_owned else R.drawable.quiz_rookie_locked
                "streak starter" -> if (achievement.isAchieved) R.drawable.streak_starter_owned else R.drawable.streak_starter_locked
                "lucky guess" -> if (achievement.isAchieved) R.drawable.lucky_guess_owned else R.drawable.lucky_guess_locked
                "explorer" -> if (achievement.isAchieved) R.drawable.explorer_owned else R.drawable.explorer_locked
                "trivia champ" -> if (achievement.isAchieved) R.drawable.trivia_champ_owned else R.drawable.trivia_champ_locked
                "collector" -> if (achievement.isAchieved) R.drawable.collector_owned else R.drawable.collector_locked
                "legend" -> if (achievement.isAchieved) R.drawable.legend_owned else R.drawable.legend_locked
                "collector ii" -> if (achievement.isAchieved) R.drawable.collector_ii_owned else R.drawable.collector_ii_locked
                "lucky Guess ii" -> if (achievement.isAchieved) R.drawable.lucky_guess_ii_owned else R.drawable.lucky_guess_ii_locked
                else -> if (achievement.isAchieved) R.drawable.quiz_rookie_owned else R.drawable.quiz_rookie_locked
            }
            image.setImageResource(imageRes)
        }
    }

    private fun onClickAchievement(achievements: List<AchievementsEntity>) {
        val detailsBinding = binding.achievementList
        detailsBinding.QuizRookie.root.setOnClickListener { presenter.goToDetails(achievements[0]) }
        detailsBinding.StreakStarter.root.setOnClickListener { presenter.goToDetails(achievements[1]) }
        detailsBinding.LuckyGuess.root.setOnClickListener { presenter.goToDetails(achievements[2]) }
        detailsBinding.Explorer.root.setOnClickListener { presenter.goToDetails(achievements[3]) }
        detailsBinding.TriviaChamp.root.setOnClickListener { presenter.goToDetails(achievements[4]) }
        detailsBinding.Collector.root.setOnClickListener { presenter.goToDetails(achievements[5]) }
        detailsBinding.Legend.root.setOnClickListener { presenter.goToDetails(achievements[6]) }
        detailsBinding.Untouchable.root.setOnClickListener { presenter.goToDetails(achievements[7]) }
        detailsBinding.QuickThinker.root.setOnClickListener { presenter.goToDetails(achievements[8]) }
        detailsBinding.CollectorII.root.setOnClickListener { presenter.goToDetails(achievements[9]) }
        detailsBinding.LuckyGuessII.root.setOnClickListener { presenter.goToDetails(achievements[10]) }
    }

    private fun hideAll() {
        binding.achievementList.root.visibility = View.GONE
        binding.achievementDetails.root.visibility = View.GONE
        binding.okDetailsButton.visibility = View.GONE
        binding.okButton.visibility = View.GONE
        binding.shareButton.visibility = View.GONE
        binding.okDetailsOwnButton.visibility = View.GONE
        binding.shareOkButtonsLayout.visibility = View.GONE
    }

    fun setupListeners() {
        binding.okButton.setOnClickListener {
            dismiss()
        }
        binding.okDetailsButton.setOnClickListener {
            dismiss()
        }

        binding.shareButton.setOnClickListener {
            val message = "Qurio Quiz Game!\nI just unlocked the \"${binding.achievementDetails.name.text}\" achievement!\nCan you unlock it too? Download the app now! https://play.google.com/store/apps/details?id=${requireActivity().application.packageName}"
            shareText(message)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.okDetailsOwnButton.setOnClickListener {
            dismiss()
        }
    }

    private fun shareText(text: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, text)
        }
        startActivity(Intent.createChooser(shareIntent, "Share with friends"))
    }

}