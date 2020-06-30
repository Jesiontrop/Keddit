package com.jesiontrop.keddit.feature.news

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jesiontrop.keddit.R
import com.jesiontrop.keddit.base.repo.news.NewsAdapter
import com.jesiontrop.keddit.base.utils.data.RedditNewsItem
import com.jesiontrop.keddit.base.utils.extensions.getFriendlyTime
import com.jesiontrop.keddit.base.utils.extensions.inflate
import kotlinx.android.synthetic.main.fragment_news.*

class NewsFragment : Fragment() {

    companion object {
        const val TAG = "NewsFragment"
    }

    private val mNewsRecyclerView : RecyclerView by lazy {
        news_list
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  container?.inflate(R.layout.fragment_news, false)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mNewsRecyclerView.setHasFixedSize(true)
        mNewsRecyclerView.layoutManager = LinearLayoutManager(activity)

        initAdapter()

        if (savedInstanceState == null) {
            val news = (1..10).map { RedditNewsItem(
                "author$it",
                "Title $it",
                it,
                1457207701L - it * 200,
                "https://picsum.photos/200/200?image=$it",
                "url") }

            for (i in 0..9) {
                Log.i(TAG, "${news[i].thumbnail}")
            }
            (news_list.adapter as NewsAdapter).addNews(news)
        }
    }

    private fun initAdapter() {
        if (news_list.adapter == null)
            news_list.adapter = NewsAdapter()
    }
}
