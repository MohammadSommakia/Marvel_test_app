package com.mylivn.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mylivn.commons.ui.base.BaseItemViewHolder
import com.mylivn.domain.models.comic.Comic
import com.mylivn.feed.databinding.ComicListItemBinding


class ComicAdapter :
    ListAdapter<Comic, ComicAdapter.ComicViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicViewHolder {
        val binding =
            ComicListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ComicViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ComicViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it, position) }


    }

    class ComicViewHolder(private val binding: ComicListItemBinding) :
        BaseItemViewHolder<Comic>(binding.root) {
        override fun bind(data: Comic, position: Int) {
            binding.model = data
//            Glide.with(context)
//                .load(movies.getPoster())
//                .into(binding.poster)
        }


    }


    companion object {
        private val itemCallback: DiffUtil.ItemCallback<Comic> =
            object : DiffUtil.ItemCallback<Comic>() {
                override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
                    return oldItem == newItem
                }
            }
    }

}