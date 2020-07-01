package com.jesiontrop.keddit.base.repo.news

import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import io.reactivex.Observable

class NewsManager() {

    fun getNews(): Observable<List<RedditNewsItem>> {
        return Observable.create {
            subscriber ->

            val news = (1..10).map { RedditNewsItem(
                "author$it",
                "Title $it",
                it,
                1457207701L - it * 200,
                "https://picsum.photos/200/200?image=$it",
                "url") }
            
            subscriber.onNext(news)
            subscriber.onComplete()
        }

    }
}