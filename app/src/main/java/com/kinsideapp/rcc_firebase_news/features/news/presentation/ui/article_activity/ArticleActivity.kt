package com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.article_activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.kinsideapp.rcc_firebase_news.R
import com.kinsideapp.rcc_firebase_news.core.route.RouteToArticle
import com.kinsideapp.rcc_firebase_news.databinding.ActivityArticleBinding
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.presentation.viewmodel.ArticleActivityViewModel

class ArticleActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityArticleBinding
    private lateinit var _viewModel: ArticleActivityViewModel
    private var _article: ArticleEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityArticleBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[ArticleActivityViewModel::class.java]
        enableEdgeToEdge()
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }

    override fun onDestroy() {
        super.onDestroy()
        RouteToArticle.article = null
    }

    /***** CORE *****/
    private fun init() {
        getRouteArgs()
        setArticleDataToUi()
        eventNavigationButtonClick()
    }

    private fun getRouteArgs() {
        _article = RouteToArticle.article
    }

    private fun setArticleDataToUi() {
        _binding.title.text = _article?.title
        _binding.article.text = _article?.article
        Glide.with(this).load(_article?.image).into(_binding.image)
    }
    /***** -END- CORE *****/

    /***** EVENTS *****/
    private fun eventNavigationButtonClick() {
        _binding.toolBar.setNavigationOnClickListener { finish() }
    }

    /***** -END- EVENTS *****/
}