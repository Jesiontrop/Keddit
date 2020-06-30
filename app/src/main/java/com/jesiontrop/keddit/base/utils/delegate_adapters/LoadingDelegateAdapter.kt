package com.jesiontrop.keddit.base.utils.delegate_adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesiontrop.keddit.R
import com.jesiontrop.keddit.base.utils.extensions.inflate

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder,
                                    item: ViewType) {}

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item_loading)) {}
}