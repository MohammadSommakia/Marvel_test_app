@file:Suppress("DEPRECATION")

package com.mylivn.data.repositories

import com.mylivn.data.BuildConfig
import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.data.api.util.ApiHelper
import com.mylivn.data.mappers.CharacterMapper
import com.mylivn.data.util.Extension.Companion.md5
import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.character.Character
import com.mylivn.domain.repositories.CharacterRepository
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val marvelApiService: MarvelApiService,
    private val characterMapper: CharacterMapper,
) : CharacterRepository {
    override suspend fun getRemoteCharacters(offset: Int, pageSize: Int): Result<List<Character>> {
        return apiHelper.safeExecute(
            block = {
                val ts = System.currentTimeMillis()
                val hash =
                    "${System.currentTimeMillis()}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()!!
                marvelApiService.getCharacters(
                    ts,
                    BuildConfig.PUBLIC_API_KEY,
                    hash,
                    pageSize,
                    offset
                )
            },
            transform = {
                characterMapper.map(it.data!!.results)
            },
            persist = { characters ->
                characters
            }
        )
    }


}