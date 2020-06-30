package com.jesiontrop.keddit.base.repo.news

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.jesiontrop.keddit.base.utils.constants.AdapterConstants
import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import com.jesiontrop.keddit.base.utils.delegate_adapters.LoadingDelegateAdapter
import com.jesiontrop.keddit.base.utils.delegate_adapters.NewsDelegateAdapter
import com.jesiontrop.keddit.base.utils.delegate_adapters.ViewType
import com.jesiontrop.keddit.base.utils.delegate_adapters.ViewTypeDelegateAdapter

class NewsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.NEWS, NewsDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))
            ?.onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addNews(news: List<RedditNewsItem>) {

        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1)
    }

    fun clearAndAddNews(news: List<RedditNewsItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(news)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getNews(): List<RedditNewsItem> {
        return items
            .filter { it.getViewType() == AdapterConstants.NEWS }
            .map { it as RedditNewsItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex
}