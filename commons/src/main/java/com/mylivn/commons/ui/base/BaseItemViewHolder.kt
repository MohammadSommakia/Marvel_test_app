package com.mylivn.commons.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder<in Data>(
    itemView: View

    ) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(data: Data, position: Int)



}

