package com.kinsideapp.rcc_firebase_news.features.news.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.kinsideapp.rcc_firebase_news.features.news.data.repository.NewsRepositoryImp
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.domain.usecase.NewsUseCase

class NewsActivityViewModel : ViewModel() {
    fun getNews(
        onSuccess: (newsList: List<ArticleEntity>) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        NewsUseCase(NewsRepositoryImp()).getAllNews(onSuccess, onFailure)
    }
}