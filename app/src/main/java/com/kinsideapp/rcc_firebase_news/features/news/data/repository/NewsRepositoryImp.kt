package com.kinsideapp.rcc_firebase_news.features.news.data.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kinsideapp.rcc_firebase_news.core.FIREBASE_COLLECTION
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
                onSuccess(records.toObjects(ArticleEntity::class.java))
            }
            .addOnFailureListener { onFailure("Failed to read news") }
    }

    override fun deleteSingleNews(
        newsId: String,
        onSuccess: () -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        val recordRef = Firebase.firestore.collection(FIREBASE_COLLECTION).document(newsId)
        //read record to get image reference
        recordRef.get().addOnSuccessListener {
            val imageUrl = it.toObject(ArticleEntity::class.java)?.image ?: ""
            //delete record
            recordRef.delete().addOnSuccessListener {
                //delete image
                Firebase.storage.getReferenceFromUrl(imageUrl).delete()
            }.addOnSuccessListener { onSuccess() }
                .addOnFailureListener { onFailure("Failed to delete article !") }
        }
    }


}