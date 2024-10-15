package com.kinsideapp.rcc_firebase_news.features.news.domain.usecase

import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.NewsEntity

class NewsUseCase {
    fun getAllNews(): NewsEntity {
        return NewsEntity()
    }

    fun deleteArticle(articleId: String) {

    }

    fun deleteAllArticles() {

    }
}