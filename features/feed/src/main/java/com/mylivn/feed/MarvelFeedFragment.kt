package com.mylivn.feed

import androidx.recyclerview.widget.DividerItemDecoration
import com.mylivn.commons.ui.base.BaseFragment
import com.mylivn.domain.models.character.Character
import com.mylivn.feed.adapter.*
import com.mylivn.feed.databinding.FragmentMarvelFeedBinding
import javax.inject.Inject


class MarvelFeedFragment :
    BaseFragment<FragmentMarvelFeedBinding, MarvelFeedViewModel>(R.layout.fragment_marvel_feed),
    CharacterAdapter.OnItemClickListener<Character> {


    private var selectedCharacter: Character? = null

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    @Inject
    lateinit var comicAdapter: ComicAdapter

    @Inject
    lateinit var eventAdapter: EventAdapter

    @Inject
    lateinit var seriesAdapter: SeriesAdapter

    @Inject
    lateinit var storiesAdapter: StoriesAdapter

    override fun onInitDataBinding() {

        viewBinding.viewModel = viewModel

    }

    override fun initView() {
        characterAdapter.registerClickListener(this)
        viewBinding.recyclerViewCharacter.adapter = characterAdapter
        viewBinding.recyclerViewComic.adapter = comicAdapter
        viewBinding.recyclerViewEvents.adapter = eventAdapter
        viewBinding.recyclerViewSeries.adapter = seriesAdapter
        viewBinding.recyclerViewStories.adapter = storiesAdapter


        // add dividers between RecyclerView's row items
        val dividerItem = DividerItemDecoration(requireContext(), DividerItemDecoration.HORIZONTAL)
        viewBinding.recyclerViewCharacter.addItemDecoration(dividerItem)
        viewBinding.recyclerViewComic.addItemDecoration(dividerItem)
        viewBinding.recyclerViewEvents.addItemDecoration(dividerItem)
        viewBinding.recyclerViewSeries.addItemDecoration(dividerItem)
        viewBinding.recyclerViewStories.addItemDecoration(dividerItem)


    }


    override fun getData() {

        viewModel.getPagedCharacters()

        viewModel.successData.observe(viewLifecycleOwner) {
            characterAdapter.submitList(it)
            viewModel.getPagedComics(it[0].id, 1)
            viewModel.getPagedEvents(it[0].id, 1)
            viewModel.getPagedSeries(it[0].id, 1)
            viewModel.getPagedStories(it[0].id, 1)
        }

        viewModel.comicsLiveData.observe(viewLifecycleOwner) {
            comicAdapter.submitList(it)
        }

        viewModel.eventsLiveData.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }

        viewModel.eventsLiveData.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }
        viewModel.seriesLivaData.observe(viewLifecycleOwner) {
            seriesAdapter.submitList(it)
        }
        viewModel.storiesLiveData.observe(viewLifecycleOwner) {
            storiesAdapter.submitList(it)
        }
    }

    override fun onItemClick(item: Character) {
        this.selectedCharacter = item
        viewModel.getPagedComics(item.id, 1)
        viewModel.getPagedEvents(item.id, 1)
        viewModel.getPagedSeries(item.id, 1)
        viewModel.getPagedStories(item.id, 1)
    }


}

