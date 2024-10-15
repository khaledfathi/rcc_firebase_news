package com.kinsideapp.rcc_firebase_news.features.news.data.repository

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.domain.repository.NewsRepository

class NewsRepositoryImp:NewsRepository {
    override fun getAllNews(): List<ArticleEntity> {
        TODO("Not yet implemented")
    }

    override fun deleteAll() {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: String) {
        TODO("Not yet implemented")
    }
}