package com.kinsideapp.rcc_firebase_news.features.news.domain.entity

import com.kinsideapp.rcc_firebase_news.features.news.data.model.ArticleModel

data class NewsEntity(
    val status: Boolean = false,
    val  articles:List<ArticleModel> = listOf(),
    val error: String = "",
)