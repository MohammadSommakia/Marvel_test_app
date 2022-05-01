package com.mylivn.feed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mylivn.commons.ui.base.BaseItemViewHolder
import com.mylivn.domain.models.event.Event
import com.mylivn.feed.databinding.EventListItemBinding

class EventAdapter :
    ListAdapter<Event, EventAdapter.EventViewHolder>(itemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding =
            EventListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        currentList[position]?.let { holder.bind(it, position) }


    }

    class EventViewHolder(private val binding: EventListItemBinding) :
        BaseItemViewHolder<Event>(binding.root) {
        override fun bind(data: Event, position: Int) {
            binding.model = data
//            Glide.with(context)
//                .load(movies.getPoster())
//                .into(binding.poster)
        }


    }


    companion object {
        private val itemCallback: DiffUtil.ItemCallback<Event> =
            object : DiffUtil.ItemCallback<Event>() {
                override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
                    return oldItem == newItem
                }
            }
    }

}