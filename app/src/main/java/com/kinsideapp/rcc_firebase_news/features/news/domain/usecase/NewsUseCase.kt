package com.kinsideapp.rcc_firebase_news.features.news.domain.usecase

import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.domain.repository.NewsRepository

class NewsUseCase(private val repository: NewsRepository) {
    fun getAllNews(
        onSuccess: (newsList: List<ArticleEntity>) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        repository.getAllNews(onSuccess, onFailure)
    }

    fun deleteSingleNews(
        newsId: String,
        onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        repository.deleteSingleNews(
            newsId,
            onSuccess,
            onFailure,
        )
    }

}