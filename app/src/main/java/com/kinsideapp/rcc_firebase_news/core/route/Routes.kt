package com.kinsideapp.rcc_firebase_news.core.route

import android.content.Context
import com.kinsideapp.rcc_firebase_news.features.add_article.presentation.ui.AddArticleActivity
import com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.NewsActivity

class RouteToAddArticle(context: Context) : Route(context) {
    override fun destination() = AddArticleActivity::class.java
}

class RouteToNews(context: Context) : Route(context) {
    override fun destination() = NewsActivity::class.java
}