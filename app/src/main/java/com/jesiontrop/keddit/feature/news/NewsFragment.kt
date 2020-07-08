package com.jesiontrop.keddit.feature.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jesiontrop.keddit.R
import com.jesiontrop.keddit.base.repo.news.NewsAdapter
import com.jesiontrop.keddit.base.repo.news.NewsManager
import com.jesiontrop.keddit.base.utils.common.InfiniteScrollListener
import com.jesiontrop.keddit.base.utils.data.RedditNews
import com.jesiontrop.keddit.base.utils.extensions.inflate
import com.jesiontrop.keddit.core_ui.RxBaseFragment
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : RxBaseFragment() {

    companion object {
        private const val TAG = "NewsFragment"
        private const val KEY_REDDIT_NEWS = "redditNews"
    }

    private val mNewsRecyclerView : RecyclerView by lazy { news_list }
    private val newsManager by lazy {NewsManager()}
    private var redditNews: RedditNews? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  container?.inflate(R.layout.fragment_news, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mNewsRecyclerView.apply {
            setHasFixedSize(true)
            val linearLayout = LinearLayoutManager(activity)
            layoutManager = linearLayout
            clearOnScrollListeners()
            addOnScrollListener(InfiniteScrollListener({ requestNews()}, linearLayout))
        }

        initAdapter()

        if (savedInstanceState != null) {
            redditNews = savedInstanceState.get(KEY_REDDIT_NEWS) as RedditNews
            (mNewsRecyclerView.adapter as NewsAdapter).clearAndAddNews(redditNews!!.news)
        } else {
            requestNews()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val news = (mNewsRecyclerView.adapter as NewsAdapter).getNews()
        if (redditNews != null && news.size > 0) {
            outState.putParcelable(KEY_REDDIT_NEWS, redditNews?.copy(news = news))
        }
    }

    private fun requestNews() {
        val subscription = newsManager.getNews(redditNews?.after ?: "")
            .subscribeOn(Schedulers.io())
            .subscribe(
                { retrievedNews ->
                    redditNews = retrievedNews
                    (news_list.adapter as NewsAdapter).addNews(retrievedNews.news)
                },
                {e ->
                    Snackbar.make(news_list, e.message ?: "", Snackbar.LENGTH_LONG).show()
                }
            )
        disposables.add(subscription)
    }

    private fun initAdapter() {
        if (news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}
