package com.qurio.data.repositoryImpl

import com.qurio.data.local.dao.CharactersDao
import com.qurio.data.local.entity.CharactersEntity
import com.qurio.data.repository.CharactersRepository
import jakarta.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val dao: CharactersDao
) : CharactersRepository {

    override suspend fun initCharacters() {
        if (dao.getCount() == 0) {
            val defaultCharacters = listOf(
                CharactersEntity(
                    id = 1,
                    name = "Rika",
                    image = "avatar_rika",
                    lockedImage = "avatar_rika",
                    price = 0,
                    age = "Age: 12 Sunblooms",
                    isOwned = true,
                    isSelected = true,
                    description = "Nature's little explorer! Rika talks to mushrooms and swears squirrels give her battle advice. Always ready for an adventure."
                ),
                CharactersEntity(
                    id = 2,
                    name = "Kaiyo",
                    image = "avatar_kaiyo",
                    lockedImage = "ch_kaiyo_locked",
                    price = 300,
                    age = "Age: 14 Storms",
                    isOwned = false,
                    isSelected = false,
                    description = "A calm storm in human form. Kaiyo trains with ancient scrolls by day and drinks spicy tea by night. Sword sharp, heart sharper."
                ),
                CharactersEntity(
                    id = 3,
                    name = "Mimi",
                    image = "avatar_mimi",
                    lockedImage = "ch_mimi_locked",
                    price = 700,
                    age = "Age: 10 Volcano Puffs",
                    isOwned = false,
                    isSelected = false,
                    description = "Tiny but terrifying! Mimi is always grumpy, but don't let that scare you—unless you like pranks involving firecrackers."
                ),
                CharactersEntity(
                    id = 4,
                    name = "Yoru",
                    image = "avatar_yoru",
                    lockedImage = "ch_yoru_locked",
                    price = 1000,
                    age = "Age: 13 Shadows",
                    isOwned = false,
                    isSelected = false,
                    description = "Quiet, mysterious, and probably watching you right now. Yoru shows up when you least expect it."
                ),
                CharactersEntity(
                    id = 5,
                    name = "Kuro",
                    image = "avatar_kuro",
                    lockedImage = "ch_kuro_locked",
                    price = 3000,
                    age = "Age: 15 Thunder Beats",
                    isOwned = false,
                    isSelected = false,
                    description = "Cool jacket, cooler moves. Kuro never backs down from a challenge."
                ),
                CharactersEntity(
                    id = 6,
                    name = "Miko",
                    image = "avatar_kuro",
                    lockedImage = "ch_kuro_locked",
                    price = 7000,
                    age = "Age: 11 Leaf Turns",
                    isOwned = false,
                    isSelected = false,
                    description = "Energetic, cheerful, and faster than a leaf in the wind. Miko can turn any trivia into a giggle-fest."
                ),
                CharactersEntity(
                    id = 7,
                    name = "Aori",
                    image = "avatar_aori",
                    lockedImage = "ch_aori_locked",
                    price = 12000,
                    age = "Age: 13 Blade Echoes",
                    isOwned = false,
                    isSelected = false,
                    description = "The sword chooses the wielder—and it chose Aori. Calm, focused."
                ),
                CharactersEntity(
                    id = 8,
                    name = "Nara",
                    image = "avatar_nara",
                    lockedImage = "ch_nara_locked",
                    price = 30000,
                    age = "Age: 12 Crystal Songs",
                    isOwned = false,
                    isSelected = false,
                    description = "Part magic, part sass. Nara sparkles even when she's mad."
                ),
                CharactersEntity(
                    id = 9,
                    name = "Renji",
                    image = "avatar_renji",
                    lockedImage = "ch_renji_locked",
                    price = 50000,
                    age = "Age: 11 Hero Coins",
                    isOwned = false,
                    isSelected = false,
                    description = "Small but mighty! Renji dreams of glory, carries a shield too big for him."
                )
            )

            dao.insertAll(defaultCharacters)
        }
    }

    override suspend fun getAllCharacters(): List<CharactersEntity> {
        return dao.getAllCharacters()
    }

    override suspend fun buyCharacter(characterId: Int) {
        dao.buyCharacter(characterId)
    }

    override suspend fun getSelectedCharacter(): CharactersEntity {
        return dao.getSelectedCharacter()
    }

}