package com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.AddArticleResponseEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity

interface AddArticleRepository {
    fun addNewArticle(article:ArticleEntity):AddArticleResponseEntity
}