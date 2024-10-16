package com.kinsideapp.rcc_firebase_news.features.add_article.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kinsideapp.rcc_firebase_news.features.add_article.data.repository.AddArticleRepositoryImp
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.usecase.AddArticleUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class AddArticleActivityViewModel : ViewModel() {

    fun addArticle(
        title: String,
        article: String,
        image: String,
        onSuccess: (message: String) -> Unit,
        onFailure: (error: String) -> Unit,
    ) {
        AddArticleUseCase(AddArticleRepositoryImp()).uploadNewArticle(
            ArticleEntity(
                title = title,
                article = article,
                image = image
            ),
            onSuccess,
            onFailure
        )

    }


}