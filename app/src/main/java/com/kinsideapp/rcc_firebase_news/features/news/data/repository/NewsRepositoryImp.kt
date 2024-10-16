package com.kinsideapp.rcc_firebase_news.features.news.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kinsideapp.rcc_firebase_news.core.global.FIREBASE_COLLECTION
import com.kinsideapp.rcc_firebase_news.features.news.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.news.domain.repository.NewsRepository

class NewsRepositoryImp : NewsRepository {
    override fun getAllNews(
        onSuccess: (newsList: List<ArticleEntity>) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        Firebase.firestore
            .collection(FIREBASE_COLLECTION)
            .get()
            .addOnSuccessListener { records ->
                val news = mutableListOf<ArticleEntity>()
                for (record in records) {
                    news.add(
                        ArticleEntity(
                            id = record.id,
                            title = record.data["title"].toString(),
                            article = record.data["article"].toString(),
                            image = record.data["image"].toString(),
                        )
                    )
                }
                onSuccess(news)
            }
            .addOnFailureListener { onFailure("Fail to read news") }
    }

}