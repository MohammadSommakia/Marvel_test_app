package com.mylivn.feed.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.mylivn.commons.ui.base.BaseItemViewHolder
import com.mylivn.domain.models.character.Character
import com.mylivn.feed.databinding.CharacterListItemBinding


class CharacterAdapter :
    ListAdapter<Character, CharacterAdapter.CharacterViewHolder>(itemCallback) {

    var listener: OnItemClickListener<Character>? = null
    var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CharacterViewHolder {
        context = parent.context
        val binding =
            CharacterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    fun registerClickListener(listener: OnItemClickListener<Character>) {
        this.listener = listener

    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it, position) }

    }

    inner class CharacterViewHolder(
        private val binding: CharacterListItemBinding
    ) :
        BaseItemViewHolder<Character>(binding.root), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        override fun bind(data: Character, position: Int) {
            binding.model = data
//            Glide.with(context!!)
//                .load(data.imagePath)
//                .into(binding.characterImageView)

            if (data.isSelected)
                binding.characterImageView.borderColor =
                    context!!.getColor(com.mylivn.domain.R.color.black)
            else
                binding.characterImageView.borderColor =
                    context!!.getColor(com.mylivn.domain.R.color.white)

        }

        override fun onClick(p0: View?) {
            getItem( absoluteAdapterPosition)?.let { listener?.onItemClick(it) }
        }


    }



    companion object {
        private val itemCallback: DiffUtil.ItemCallback<Character> =
            object : DiffUtil.ItemCallback<Character>() {
                override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Character,
                    newItem: Character
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)
    }
}
