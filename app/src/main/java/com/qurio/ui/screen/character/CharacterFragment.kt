package com.qurio.ui.screen.character

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.qurio.QurioApp
import com.qurio.R
import com.qurio.data.local.entity.CharactersEntity
import com.qurio.databinding.DialogCharacterBinding
import com.qurio.databinding.ItemCharacterBinding
import com.qurio.ui.base.BaseDialogFragment
import javax.inject.Inject

class CharacterFragment : BaseDialogFragment<DialogCharacterBinding>(DialogCharacterBinding::inflate),
    CharacterContract.View {

    @Inject
    lateinit var presenter: CharacterContract.Presenter
    private var selectedCharacterId = 0
    private lateinit var selectedCharacter: CharactersEntity
    private var currentPage = "character"
    private var userPoints = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp)
            .appComponent
            .inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attachView(this)
        presenter.getAllCharacters()
        presenter.getUserPoints()
        setupListener()
    }

    override fun onDestroyView() {
        presenter.detachView()
        super.onDestroyView()
    }


    override fun showCharacters(characters: List<CharactersEntity>) {
        binding.layoutConfirmCancelButtons.confirmLayout.visibility = View.VISIBLE
        val characterBindings = listOf(
            binding.charactersList.rika,
            binding.charactersList.kaiyo,
            binding.charactersList.mimi,
            binding.charactersList.yoru,
            binding.charactersList.kuro,
            binding.charactersList.miko,
            binding.charactersList.aori,
            binding.charactersList.nara,
            binding.charactersList.renji
        )
        onClickCharacter(characters)
        characterBindings.zip(characters).forEach { (charBinding, character) ->
            bindCharacterView(charBinding, character)
        }
    }

    override fun showDetailsPage(characters: CharactersEntity,  isOwned: Boolean) {
        currentPage = "details"
        selectedCharacter = characters
        val imageRes = when (characters.name.lowercase()) {
            "rika" -> R.drawable.ch_rika_fill
            "kaiyo" -> R.drawable.ch_kaiyo_fill
            "mimi" -> R.drawable.ch_mimi_fill
            "yoru" -> R.drawable.ch_yoru_fill
            "kuro" -> R.drawable.ch_kuro_fill
            "miko" -> R.drawable.ch_miko_fill
            "aori" -> R.drawable.ch_aori_fill
            "nara" -> R.drawable.ch_nara_fill
            "renji" -> R.drawable.ch_renji_fill
            else -> R.drawable.ch_rika_fill
        }

        hideAll()
        binding.details.root.visibility = View.VISIBLE
        binding.details.name.text = characters.name
        binding.details.age.text = characters.age
        binding.details.description.text = characters.description
        binding.details.image.setImageResource(imageRes)
        binding.details.lock.visibility
        if (isOwned) {
            binding.details.lock.visibility = View.GONE
            binding.layoutOkButton.okLayout.visibility = View.VISIBLE
            binding.layoutOkButton.root.visibility = View.VISIBLE
        }else {
            binding.details.lock.visibility = View.VISIBLE
            binding.layoutBuyOkButton.root.visibility = View.VISIBLE
            binding.layoutBuyOkButton.buyOkLayout.visibility = View.VISIBLE
        }
    }

    override fun showBuyCharacter(character: CharactersEntity) {
        hideAll()
        val imageRes = when (character.name.lowercase()) {
            "rika" -> R.drawable.ch_rika_fill
            "kaiyo" -> R.drawable.ch_kaiyo_fill
            "mimi" -> R.drawable.ch_mimi_fill
            "yoru" -> R.drawable.ch_yoru_fill
            "kuro" -> R.drawable.ch_kuro_fill
            "miko" -> R.drawable.ch_miko_fill
            "aori" -> R.drawable.ch_aori_fill
            "nara" -> R.drawable.ch_nara_fill
            "renji" -> R.drawable.ch_renji_fill
            else -> R.drawable.ch_rika_fill
        }
        binding.buy.root.visibility = View.VISIBLE
        binding.buy.lock.visibility = View.VISIBLE
        binding.buy.image.setImageResource(imageRes)
        binding.buy.price.text = character.price.toString()
        binding.layoutBuyCancelButton.root.visibility = View.VISIBLE
        binding.layoutBuyCancelButton.buyCancelLayout.visibility = View.VISIBLE
        binding.layoutBuyCancelButton.buyCancelButton.visibility = View.VISIBLE
        if (userPoints >= character.price ){
            binding.layoutBuyCancelButton.buyCharacterCancelButton.visibility = View.VISIBLE
            binding.layoutBuyCancelButton.buyCancelButton.isEnabled = true
        }else {
            binding.layoutBuyCancelButton.buyCancelButton.background = resources.getDrawable(R.drawable.primary_button_disabled_bg, null)
            binding.layoutBuyCancelButton.buyCancelButton.isEnabled = false
        }
    }

    override fun getUserPoints(points: Int) {
        userPoints = points
    }

    override fun exit() {
        dismiss()
    }

    fun hideAll() {
        binding.let {
            it.charactersList.root.visibility = View.GONE
            it.details.root.visibility = View.GONE
            it.buy.root.visibility = View.GONE
            it.layoutOkButton.root.visibility = View.GONE
            it.layoutBuyOkButton.root.visibility = View.GONE
            it.layoutBuyCancelButton.root.visibility = View.GONE
            it.layoutConfirmCancelButtons.root.visibility = View.GONE
        }
    }

    private fun bindCharacterView(
        characterBinding: ItemCharacterBinding,
        character: CharactersEntity
    ) {
        with(characterBinding) {
            characterName.text = character.name

            val nameColor = if (character.isSelected) {
                R.color.Primary
            } else if (character.isOwned) {
                R.color.On_primary
            } else {
                R.color.Shade_Secondary
            }
            characterName.setTextColor(resources.getColor(nameColor, null))

            checkedIcon.visibility = if (character.isSelected) View.VISIBLE else View.GONE

            val imageRes = when (character.name.lowercase()) {
                "rika" -> if (character.isOwned) R.drawable.avatar_rika else R.drawable.avatar_rika
                "kaiyo" -> if (!character.isOwned) R.drawable.ch_kaiyo_locked else R.drawable.avatar_kaiyo
                "mimi" -> if (!character.isOwned) R.drawable.ch_mimi_locked else R.drawable.avatar_mimi
                "yoru" -> if (!character.isOwned) R.drawable.ch_yoru_locked else R.drawable.avatar_yoru
                "kuro" -> if (!character.isOwned) R.drawable.ch_kuro_locked else R.drawable.avatar_kuro
                "miko" -> if (!character.isOwned) R.drawable.ch_miko_locked else R.drawable.avatar_miko
                "aori" -> if (!character.isOwned) R.drawable.ch_aori_locked else R.drawable.avatar_aori
                "nara" -> if (!character.isOwned) R.drawable.ch_nara_locked else R.drawable.avatar_nara
                "renji" -> if (!character.isOwned) R.drawable.ch_renji_locked else R.drawable.avatar_renji
                else -> R.drawable.avatar_rika
            }
            characterAvatar.setImageResource(imageRes)
        }
    }



    fun onClickCharacter(characters: List<CharactersEntity>) {
        binding.charactersList.rika.root.setOnClickListener {
            if (characters[0].isOwned) {
                selectedCharacterId = 0
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.rika.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.rika.characterName.setTextColor(resources.getColor(R.color.Primary))
            }
        }
        binding.charactersList.kaiyo.root.setOnClickListener {
            if (characters[1].isOwned) {
                selectedCharacterId = 1
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.kaiyo.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.kaiyo.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[1], false)
            }
        }
        binding.charactersList.mimi.root.setOnClickListener {
            if (characters[2].isOwned) {
                selectedCharacterId = 2
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.mimi.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.mimi.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[2], false)
            }
        }
        binding.charactersList.yoru.root.setOnClickListener {
            if (characters[3].isOwned) {
                selectedCharacterId = 3
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.yoru.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.yoru.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[3], false)
            }
        }
        binding.charactersList.kuro.root.setOnClickListener {
            if (characters[4].isOwned) {
                selectedCharacterId = 4
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.kuro.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.kuro.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[4], false)
            }
        }
        binding.charactersList.miko.root.setOnClickListener {
            if (characters[5].isOwned) {
                selectedCharacterId = 5
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.miko.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.miko.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[5], false)
            }
        }
        binding.charactersList.aori.root.setOnClickListener {
            if (characters[6].isOwned) {
                selectedCharacterId = 6
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.aori.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.aori.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[6], false)
            }
        }
        binding.charactersList.nara.root.setOnClickListener {
            if (characters[7].isOwned) {
                selectedCharacterId = 7
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.nara.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.nara.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[7], false)
            }
        }
        binding.charactersList.renji.root.setOnClickListener {
            if (characters[8].isOwned) {
                selectedCharacterId = 8
                presenter.goToDetailsPage(characters[selectedCharacterId])
                binding.charactersList.renji.checkedIcon.visibility = View.VISIBLE
                binding.charactersList.renji.characterName.setTextColor(resources.getColor(R.color.Primary))
            } else {
                presenter.goToDetailsPage(characters[8], false)
            }
        }
    }
    fun setupListener() {
        binding.layoutConfirmCancelButtons.cancelButton.setOnClickListener {
            if (currentPage == "character") {
                Toast.makeText(requireContext(), "Cancel in character", Toast.LENGTH_SHORT).show()
                dismiss()
            }else {
                Toast.makeText(requireContext(), "Cancel in buy", Toast.LENGTH_SHORT).show()
                presenter.getAllCharacters()
            }
        }
        binding.layoutConfirmCancelButtons.confirmButton.setOnClickListener {
            Toast.makeText(requireContext(), "Confirm", Toast.LENGTH_SHORT).show()
            presenter.buyCharacter(selectedCharacter)
        }
        binding.layoutOkButton.okButton.setOnClickListener {
            Toast.makeText(requireContext(), "Ok", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.layoutBuyOkButton.okBuyButton.setOnClickListener {
            Toast.makeText(requireContext(), "ok in details", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.layoutBuyOkButton.buyButton.setOnClickListener {
            Toast.makeText(requireContext(), "Buy in details", Toast.LENGTH_SHORT).show()
            presenter.goToBuyCharacter(selectedCharacter)
        }
        binding.layoutBuyCancelButton.buyCharacterCancelButton.setOnClickListener {
            Toast.makeText(requireContext(), "cancel in buy character", Toast.LENGTH_SHORT).show()
            dismiss()
        }
        binding.layoutBuyCancelButton.buyCancelButton.setOnClickListener{
            Toast.makeText(requireContext(), "Buy in buy character", Toast.LENGTH_SHORT).show()
            dismiss()
        }
    }

    override fun showLoading() {}
    override fun hideLoading() {}
    override fun showError(message: String) {}
}
