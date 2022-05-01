package com.mylivn.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mylivn.commons.ui.base.BaseItemViewHolder
import com.mylivn.domain.models.story.Story
import com.mylivn.feed.databinding.StoryListItemBinding

class StoriesAdapter : ListAdapter<Story, StoriesAdapter.StoriesViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
        val binding =
            StoryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return StoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it, position) }


    }

    class StoriesViewHolder(private val binding: StoryListItemBinding) :
        BaseItemViewHolder<Story>(binding.root) {
        override fun bind(data: Story, position: Int) {
            binding.model = data
//            Glide.with(context)
//                .load(movies.getPoster())
//                .into(binding.poster)
        }


    }


    companion object {
        private val itemCallback: DiffUtil.ItemCallback<Story> =
            object : DiffUtil.ItemCallback<Story>() {
                override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
                    return oldItem == newItem
                }
            }
    }

}
