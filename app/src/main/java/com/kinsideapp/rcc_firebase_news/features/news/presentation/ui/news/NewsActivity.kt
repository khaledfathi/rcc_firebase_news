package com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.news

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import com.kinsideapp.rcc_firebase_news.core.global.BaseActivity
import com.kinsideapp.rcc_firebase_news.databinding.ActivityNewsBinding
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.presentation.viewmodel.NewsActivityViewModel

class NewsActivity : BaseActivity() {
    //for ui
    private lateinit var _binding: ActivityNewsBinding
    private lateinit var _viewModel: NewsActivityViewModel

    //for data
    private var _news: MutableList<ArticleEntity>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[NewsActivityViewModel::class.java]
        enableEdgeToEdge()
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    /***** CORE *****/
    private fun init() {
        readAndSetNewsList()
        eventNavigationButtonClick()
    }

    private fun readAndSetNewsList() {
        _binding.progressBar.isVisible = true
        _viewModel.getNews(
            onSuccess = { news -> actionOnReadNewsSuccess(news) },
            onFailure = { error ->
                showToastLong(error)
                _binding.progressBar.isVisible = false
            }
        )
    }
    /***** -END- CORE *****/

    /***** EVENTS *****/
    private fun eventNavigationButtonClick() {
        _binding.toolBar.setOnClickListener {
            finish()
        }
    }
    /***** -END- EVENTS *****/

    /***** ACTIONS *****/
    private fun actionOnReadNewsSuccess(news: List<ArticleEntity>) {
        _news = news as MutableList

        val adapter = NewsListAdapter(this, _news!!)
        _binding.newsRecycleView.adapter = adapter
        //on swipe action
        itemTouchOnSwipe(
            _binding.newsRecycleView,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) { position ->
            //delete from data base
            _viewModel.deleteSingleNews(
                newsId = _news!![position].id,
                onSuccess = {
                    //remove from UI
                    _news?.removeAt(position)
                    adapter.notifyItemRemoved(position)
                },
            )
        }
        _binding.progressBar.isVisible = false
    }
    /***** -END- ACTIONS *****/
}