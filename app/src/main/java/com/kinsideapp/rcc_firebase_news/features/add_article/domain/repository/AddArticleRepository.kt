package com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity

interface AddArticleRepository {
    fun addNewArticle(
        article: ArticleEntity,
        onSuccess: (message: String) -> Unit,
        onFailure: (error: String) -> Unit
    )
}