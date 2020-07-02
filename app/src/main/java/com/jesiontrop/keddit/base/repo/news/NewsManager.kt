package com.jesiontrop.keddit.base.repo.news

import com.jesiontrop.keddit.base.utils.api.RestApi
import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import io.reactivex.Observable

class NewsManager(private val api: RestApi = RestApi()) {

    fun getNews(limit: String = "10"): Observable<List<RedditNewsItem>> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getNews("", limit)
            val responce = callResponse.execute()

            if (responce.isSuccessful) {
                val news = responce.body()!!.data.children.map {
                    val item = it.data
                    RedditNewsItem(item.author, item.title, item.num_comments,
                                    item.created, item.thumbnail, item.url)
                }
                subscriber.onNext(news)
                subscriber.onComplete()
            } else {
                subscriber.onError(Throwable(responce.message()))
            }
        }

    }
}