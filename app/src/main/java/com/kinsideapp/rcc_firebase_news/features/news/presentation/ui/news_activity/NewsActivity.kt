package com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.news_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.kinsideapp.rcc_firebase_news.core.global.BaseActivity
import com.kinsideapp.rcc_firebase_news.databinding.ActivityNewsBinding
import com.kinsideapp.rcc_firebase_news.features.news.presentation.viewmodel.NewsActivityViewModel

class NewsActivity : BaseActivity() {
    private lateinit var _binding: ActivityNewsBinding
    private lateinit var _viewModel: NewsActivityViewModel
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

    private fun init() {
        readAndSetNewsList()
        eventNavigationButtonClick()
    }

    private fun readAndSetNewsList() {
        _binding.progressBar.isVisible = true
        _viewModel.getNews(
            onSuccess = { news ->
                _binding.newsRecycleView.adapter = NewsListAdapter(this, news)
                _binding.progressBar.isVisible = false
            },
            onFailure = { error ->
                showToastLong(error)
                _binding.progressBar.isVisible = false
            }
        )

    }

    private fun eventNavigationButtonClick() {
        _binding.toolBar.setOnClickListener { finish() }
    }

}