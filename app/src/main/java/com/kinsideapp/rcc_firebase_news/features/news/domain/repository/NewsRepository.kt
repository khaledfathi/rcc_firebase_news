package com.kinsideapp.rcc_firebase_news.features.news.domain.repository

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity

interface NewsRepository {
    fun getAllNews():List<ArticleEntity>
    fun deleteAll()
    fun deleteById(id:String)
}