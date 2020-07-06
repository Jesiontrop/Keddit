package com.jesiontrop.keddit.base.repo.news

import com.jesiontrop.keddit.base.utils.api.RestApi
import com.jesiontrop.keddit.base.utils.data.RedditNews
import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(after: String, limit: String = "10"): Observable<RedditNews> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews(after, limit)
            val responce = callResponse.execute()

            if (responce.isSuccessful) {
                val dataResponce = responce.body().data
                val news = dataResponce.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                                    item.created, item.thumbnail, item.url)
                }
                val redditNews = RedditNews (
                    dataResponce.after ?: "",
                    dataResponce.before ?: "",
                    news)
                subscriber.onNext(redditNews)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(responce.message()))
            }
        }

    }
}