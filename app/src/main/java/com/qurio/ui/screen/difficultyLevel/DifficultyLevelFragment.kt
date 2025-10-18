package com.qurio.ui.screen.difficultyLevel

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.qurio.R
import com.qurio.databinding.DialogDifficultyLevelBinding
import com.qurio.ui.base.BaseDialogFragment


class DifficultyLevelFragment : BaseDialogFragment<DialogDifficultyLevelBinding>(DialogDifficultyLevelBinding::inflate) {

    private val args: DifficultyLevelFragmentArgs by navArgs()
    private var selectedLevel: DifficultyLevel? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resetSelection()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding.easy.setOnClickListener { selectDifficulty(DifficultyLevel.EASY) }
        binding.medium.setOnClickListener { selectDifficulty(DifficultyLevel.MEDIUM) }
        binding.hard.setOnClickListener { selectDifficulty(DifficultyLevel.HARD) }

        binding.cancelButton.setOnClickListener { dismiss() }
        binding.exitButton.setOnClickListener { dismiss() }

        binding.confirmButton.setOnClickListener {
            selectedLevel?.let { level ->
                val action = DifficultyLevelFragmentDirections.dialogDifficultyLevelToGameFragment(
                    category = args.category,
                    difficulty = when(level) {
                        DifficultyLevel.EASY -> "easy"
                        DifficultyLevel.MEDIUM -> "medium"
                        DifficultyLevel.HARD -> "hard"
                    },
                    amount = when(level) {
                        DifficultyLevel.EASY -> 5
                        DifficultyLevel.MEDIUM -> 10
                        DifficultyLevel.HARD -> 15
                    }
                )
                findNavController().navigate(action)
                dismiss()
            }
        }

        binding
    }

    private fun selectDifficulty(level: DifficultyLevel) {
        selectedLevel = level

        binding.easy.setBackgroundResource(R.drawable.result_info_background)
        binding.medium.setBackgroundResource(R.drawable.result_info_background)
        binding.hard.setBackgroundResource(R.drawable.result_info_background)

        binding.easyText.setTextColor(resources.getColor(R.color.Shade_Secondary))
        binding.mediumText.setTextColor(resources.getColor(R.color.Shade_Secondary))
        binding.hardText.setTextColor(resources.getColor(R.color.Shade_Secondary))

        when (level) {
            DifficultyLevel.EASY -> {
                binding.easy.setBackgroundResource(R.drawable.difficulty_level_button_bg)
                binding.easyText.setTextColor(resources.getColor(R.color.On_primary))
            }
            DifficultyLevel.MEDIUM -> {
                binding.medium.setBackgroundResource(R.drawable.difficulty_level_button_bg)
                binding.mediumText.setTextColor(resources.getColor(R.color.On_primary))
            }
            DifficultyLevel.HARD -> {
                binding.hard.setBackgroundResource(R.drawable.difficulty_level_button_bg)
                binding.hardText.setTextColor(resources.getColor(R.color.On_primary))
            }
        }
        binding.confirmButton.isEnabled = true
        binding.confirmButton.background = resources.getDrawable(R.drawable.primary_button_bg)
    }

    private fun resetSelection() {
        selectedLevel = null
        binding.confirmButton.isEnabled = false
        binding.confirmButton.background = resources.getDrawable(R.drawable.primary_button_disabled_bg)
        binding.easy.setBackgroundResource(R.drawable.result_info_background)
        binding.medium.setBackgroundResource(R.drawable.result_info_background)
        binding.hard.setBackgroundResource(R.drawable.result_info_background)

        binding.easyText.setTextColor(resources.getColor(R.color.Shade_Secondary))
        binding.mediumText.setTextColor(resources.getColor(R.color.Shade_Secondary))
        binding.hardText.setTextColor(resources.getColor(R.color.Shade_Secondary))
    }
    enum class DifficultyLevel {
        EASY, MEDIUM, HARD
    }

}