package com.mylivn.domain.usecases.character

import com.mylivn.domain.models.character.Character
import com.mylivn.domain.repositories.CharacterRepository
import com.mylivn.domain.usecases.base.BaseNetworkUseCase
import com.mylivn.domain.usecases.base.UseCaseParameters
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) :
    BaseNetworkUseCase<List<Character>, GetCharactersUseCase.Params>() {


    data class Params(
        val pageNum: Int,
    ) : UseCaseParameters

    override suspend fun FlowCollector<com.mylivn.domain.models.base.Result<List<Character>>>.run(
        params: Params
    ) {

        characterRepository.getRemoteCharacters(offset = params.pageNum)

    }
}