package com.mylivn.data.repositories

import com.mylivn.data.BuildConfig
import com.mylivn.data.api.services.MarvelApiService
import com.mylivn.data.api.util.ApiHelper
import com.mylivn.data.mappers.ComicMapper
import com.mylivn.data.util.Extension.Companion.md5
import com.mylivn.domain.models.base.Result
import com.mylivn.domain.models.comic.Comic
import com.mylivn.domain.repositories.ComicsRepository
import javax.inject.Inject

class ComicsRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val marvelApiService: MarvelApiService,
    private val comicMapper: ComicMapper,

    ) : ComicsRepository {
    override suspend fun getServerComicsListByCharacterId(
        characterId: Int,
        offset: Int,
        pageSize: Int
    ): Result<List<Comic>> {

        return apiHelper.safeExecute(
            block = {
                val ts = System.currentTimeMillis()
                val hash =
                    "${System.currentTimeMillis()}${BuildConfig.PRIVATE_API_KEY}${BuildConfig.PUBLIC_API_KEY}".md5()!!
                marvelApiService.getComicsByCharacter(
                    ts,
                    BuildConfig.PUBLIC_API_KEY,
                    hash,
                    characterId,
                    pageSize,
                    offset
                )
            },
            transform = {
                comicMapper.map(it.data!!)
            },
            persist = { comics ->
                comics.forEach { comic ->
                    comic.characterId = characterId
                }
                comics
            }
        )
    }

}