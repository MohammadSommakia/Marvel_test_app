package com.mylivn.feed

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mylivn.commons.ui.base.BaseViewModel
import com.mylivn.domain.models.base.State
import com.mylivn.domain.models.base.handle
import com.mylivn.domain.models.character.Character
import com.mylivn.domain.models.comic.Comic
import com.mylivn.domain.models.event.Event
import com.mylivn.domain.models.series.Series
import com.mylivn.domain.models.story.Story
import com.mylivn.domain.usecases.character.GetCharactersUseCase
import com.mylivn.domain.usecases.comic.GetComicsUseCase
import com.mylivn.domain.usecases.event.GetEventsUseCase
import com.mylivn.domain.usecases.series.GetSeriesUseCase
import com.mylivn.domain.usecases.story.GetStoriesUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


class MarvelFeedViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
    private val getComicsUseCase: GetComicsUseCase,
    private val getEventsUseCase: GetEventsUseCase,
    private val getStoriesUseCase: GetStoriesUseCase,
    private val getSeriesUseCase: GetSeriesUseCase
) : BaseViewModel<List<Character>>() {

    val charactersLiveData = MutableLiveData<List<Character>>()
    val comicsLiveData = MutableLiveData<List<Comic>>()
    val eventsLiveData = MutableLiveData<List<Event>>()
    val storiesLiveData = MutableLiveData<List<Story>>()
    val seriesLivaData = MutableLiveData<List<Series>>()

    val charactersLoading = MutableLiveData<Boolean>()
    val comicsLoading = MutableLiveData<Boolean>()
    val eventsLoading = MutableLiveData<Boolean>()
    val storiesLoading = MutableLiveData<Boolean>()
    val seriesLoading = MutableLiveData<Boolean>()


    val charactersFailed = MutableLiveData<Boolean>()
    val comicsFailed = MutableLiveData<Boolean>()
    val eventsFailed = MutableLiveData<Boolean>()
    val storiesFailed = MutableLiveData<Boolean>()
    val seriesFailed = MutableLiveData<Boolean>()


    fun getPagedCharacters(pageNum: Int = 1) {
        viewModelScope.launch {
            getCharactersUseCase(GetCharactersUseCase.Params(pageNum)).collect { res ->
                res.handle({
                    charactersLoading.value = it is State.Loading
                }, {
                    charactersFailed.value = true
                }, {
                    charactersLiveData.value = it
                })

                res.handle(::handleState, ::handleFailure, ::handleSuccess)

            }
        }
    }

    fun getPagedComics(characterId: Int, pageNumber: Int) {
        viewModelScope.launch {
            getComicsUseCase(GetComicsUseCase.Params(pageNumber, characterId)).collect { res ->
                res.handle({
                    comicsLoading.value = it is State.Loading
                }, {
                    it?.message
                }, {
                    comicsLiveData.value = it
                })
            }
        }
    }


    fun getPagedEvents(characterId: Int, pageNumber: Int) {
        viewModelScope.launch {
            getEventsUseCase(GetEventsUseCase.Params(pageNumber, characterId)).collect { res ->
                res.handle({
                    eventsLoading.value = it is State.Loading
                }, {
                    it?.message
                }, {
                    eventsLiveData.value = it
                })
            }
        }
    }


    fun getPagedStories(characterId: Int, pageNumber: Int) {
        viewModelScope.launch {
            getStoriesUseCase(GetStoriesUseCase.Params(pageNumber, characterId)).collect { res ->
                res.handle({
                    storiesLoading.value = it is State.Loading
                }, {
                    it?.message
                }, {
                    storiesLiveData.value = it
                })
            }
        }
    }

    fun getPagedSeries(characterId: Int, pageNumber: Int) {
        viewModelScope.launch {
            getSeriesUseCase(GetSeriesUseCase.Params(pageNumber, characterId)).collect { res ->
                res.handle({
                    seriesLoading.value = it is State.Loading
                }, {
                    it?.message
                }, {
                    seriesLivaData.value = it
                })
            }
        }
    }
}