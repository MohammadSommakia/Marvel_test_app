package com.mylivn.domain.repositories

import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.comic.Comic

interface ComicsRepository {

    suspend fun getServerComicsListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int = 10
    ): Result<List<Comic>>

}