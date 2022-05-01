package com.mylivn.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mylivn.commons.ui.base.BaseItemViewHolder
import com.mylivn.domain.models.series.Series
import com.mylivn.feed.databinding.SeriesListItemBinding


class SeriesAdapter :
    ListAdapter<Series, SeriesAdapter.SeriesViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val binding =
            SeriesListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it, position) }


    }

    class SeriesViewHolder(private val binding: SeriesListItemBinding) :
        BaseItemViewHolder<Series>(binding.root) {
        override fun bind(data: Series, position: Int) {
            binding.model = data
//            Glide.with(context)
//                .load(movies.getPoster())
//                .into(binding.poster)
        }


    }


    companion object {
        private val itemCallback: DiffUtil.ItemCallback<Series> =
            object : DiffUtil.ItemCallback<Series>() {
                override fun areItemsTheSame(oldItem: Series, newItem: Series): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Series, newItem: Series): Boolean {
                    return oldItem == newItem
                }
            }
    }

}