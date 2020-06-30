package com.jesiontrop.keddit.base.utils.delegate_adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jesiontrop.keddit.R
import com.jesiontrop.keddit.base.utils.extensions.inflate
import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import com.jesiontrop.keddit.base.utils.extensions.getFriendlyTime
import com.jesiontrop.keddit.base.utils.extensions.loadImg
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.news_item)) {

        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
        }
    }
}