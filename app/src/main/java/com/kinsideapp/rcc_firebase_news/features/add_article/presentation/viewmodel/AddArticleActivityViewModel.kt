package com.kinsideapp.rcc_firebase_news.features.add_article.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kinsideapp.rcc_firebase_news.features.add_article.data.repository.AddArticleRepositoryImp
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.AddArticleResponseEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.usecase.AddArticleUseCase

class AddArticleActivityViewModel : ViewModel() {

    fun addArticle(title: String, article: String, image: String): AddArticleResponseEntity {
        return AddArticleUseCase(AddArticleRepositoryImp()).uploadNewArticle(
            ArticleEntity(
                title = title,
                article = article,
                image = image
            )
        )
    }
}