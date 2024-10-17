package com.kinsideapp.rcc_firebase_news.features.news.domain.repository

import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity

interface NewsRepository {
    fun getAllNews(
        onSuccess: (newsList: List<ArticleEntity>) -> Unit,
        onFailure: (error: String) -> Unit
    )

    fun deleteSingleNews(newsId: String, onSuccess: () -> Unit, onFailure: (error: String) -> Unit)
}