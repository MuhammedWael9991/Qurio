package com.qurio.ui.screen.character

import com.qurio.data.local.entity.CharactersEntity
import com.qurio.ui.base.BasePresenter
import com.qurio.ui.base.BaseView

interface CharacterContract {

    interface View : BaseView {
        fun showCharacters(characters: List<CharactersEntity>)
        fun showDetailsPage(characters: CharactersEntity,  isOwned: Boolean = true)
        fun showBuyCharacter(character: CharactersEntity)
        fun getUserPoints(points: Int)
        fun exit()
    }

    interface Presenter : BasePresenter<View> {
        fun getAllCharacters()
        fun currentPage()
        fun goToDetailsPage(characters: CharactersEntity,  isOwned: Boolean = true)
        fun buyCharacter(character: CharactersEntity)
        fun goToBuyCharacter(character: CharactersEntity)
        fun getUserPoints()
    }

}