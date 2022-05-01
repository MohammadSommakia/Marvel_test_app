package com.mylivn.data.mappers

import com.mylivn.domain.models.character.Character
import javax.inject.Inject

class CharacterMapper @Inject constructor() :
    BaseMapper<List<com.mylivn.data.models.response.Character>, List<Character>> {
    override fun map(response: List<com.mylivn.data.models.response.Character>): List<Character> {

        val charactersList = arrayListOf<Character>()
        response.forEach {
            charactersList.add(
                Character(
                    id = it.id,
                    name = it.name,
                    description = it.description,
                    imagePath = it.thumbnail.path,
                )
            )
        }
        return charactersList
    }
}