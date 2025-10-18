package com.qurio.ui.screen.character

import com.qurio.data.local.entity.CharactersEntity
import com.qurio.data.repository.CharactersRepository
import com.qurio.data.repository.UserRepository
import jakarta.inject.Inject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterPresenter @Inject constructor(
    private val characterRepository: CharactersRepository,
    private val userRepository: UserRepository
) : CharacterContract.Presenter {
    private var view: CharacterContract.View? = null
    private var page = 0
    private val pageSize = 3

    override fun attachView(view: CharacterContract.View) {
        this.view = view
    }

    override fun detachView() {
        view = null
    }

    override fun getAllCharacters() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val characters = characterRepository.getAllCharacters()
                view?.showCharacters(characters)
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }

    override fun currentPage() {
        if (page <= pageSize) {
            page++
        }
    }

    override fun goToDetailsPage(characters: CharactersEntity,  isOwned: Boolean) {
        view?.showDetailsPage(characters, isOwned)
    }

    override fun buyCharacter(character: CharactersEntity) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                characterRepository.buyCharacter(character.price)
                userRepository.updateUserPoints(-character.price)
                view?.exit()
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }

    override fun goToBuyCharacter(character: CharactersEntity) {
        view?.showBuyCharacter(character)
    }

    override fun getUserPoints(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val points = userRepository.getPoints()
                view?.getUserPoints(points)
            }catch (e: Exception) {
                view?.showError(e.message.toString())
            }

        }
    }
}