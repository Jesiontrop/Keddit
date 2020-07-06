package com.jesiontrop.keddit.base.utils.data

import com.jesiontrop.keddit.base.utils.constants.AdapterConstants
import com.jesiontrop.keddit.base.utils.delegate_adapters.ViewType

data class RedditNews(
    val after: String,
    val before: String,
    val news: List<RedditNewsItem>)

data class RedditNewsItem(
    val author: String,
    val title: String,
    val numComments: Int,
    val created: Long,
    val thumbnail: String,
    val url: String
) : ViewType {
    override fun getViewType() = AdapterConstants.NEWS
}