package com.kinsideapp.rcc_firebase_news.features.add_article.domain.usecase

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.AddArticleResponseEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository.AddArticleRepository

class AddArticleUseCase(private val repository: AddArticleRepository) {
    fun uploadNewArticle(article: ArticleEntity): AddArticleResponseEntity {
        return repository.addNewArticle(
            ArticleEntity(
                title = article.title.trim(),
                article = article.article.trim(),
                image = article.image
            )
        )
    }
}