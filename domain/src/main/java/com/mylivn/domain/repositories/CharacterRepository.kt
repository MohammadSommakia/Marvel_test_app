package com.mylivn.domain.repositories

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.character.Character

interface CharacterRepository {


    suspend fun getRemoteCharacters(offset: Int, pageSize: Int = 10): Result<List<Character>>

}