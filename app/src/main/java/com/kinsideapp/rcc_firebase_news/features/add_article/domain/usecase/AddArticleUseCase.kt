package com.kinsideapp.rcc_firebase_news.features.add_article.domain.usecase

import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository.AddArticleRepository

class AddArticleUseCase(private val repository: AddArticleRepository) {
    fun uploadNewArticle(
        article: ArticleEntity,
        onSuccess: (message: String) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        if (article.title.isBlank() || article.article.isBlank() || article.image.isBlank()) {
            onFailure("Title, article and image are required !")
        } else {
            repository.addNewArticle(
                ArticleEntity(
                    title = article.title.trim(),
                    article = article.article.trim(),
                    image = article.image
                ),
                onSuccess,
                onFailure
            )
        }
    }
}