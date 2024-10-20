package com.kinsideapp.rcc_firebase_news.core

import android.content.Context
import com.kinsideapp.rcc_firebase_news.features.add_article.presentation.ui.AddArticleActivity
import com.kinsideapp.rcc_firebase_news.features.camera_x.presentation.ui.CameraActivity
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.article.ArticleActivity
import com.kinsideapp.rcc_firebase_news.features.news.presentation.ui.news.NewsActivity
import com.metro_driver.core.Route
import com.metro_driver.fragment.ScreenActivity

class RouteToAddArticle(context: Context) : Route(context) {
    override fun destination() = AddArticleActivity::class.java
}

class RouteToNews(context: Context) : Route(context) {
    override fun destination() = NewsActivity::class.java
}

class RouteToArticle(context: Context) : Route(context) {
    companion object{
        var article: ArticleEntity? = null
    }
    override fun destination() = ArticleActivity::class.java
}

/***** FOR TEST *****/
class RouteToCamera(context: Context) : Route(context) {
    override fun destination() = CameraActivity::class.java
}
class RouteToScreen(context: Context) : Route(context) {
    override fun destination() = ScreenActivity::class.java
}
/***** -END- FOR TEST *****/
