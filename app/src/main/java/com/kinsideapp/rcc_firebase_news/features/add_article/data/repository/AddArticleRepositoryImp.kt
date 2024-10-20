package com.kinsideapp.rcc_firebase_news.features.add_article.data.repository

import androidx.core.net.toUri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kinsideapp.rcc_firebase_news.core.FIREBASE_COLLECTION
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository.AddArticleRepository
import com.metro_driver.core.Helper

class AddArticleRepositoryImp : AddArticleRepository {

    override fun addNewArticle(
        article: ArticleEntity,
        onSuccess: (message: String) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        val fileName = "image_${Helper.getCurrentDateTime()}"

        //upload image
        val storage = Firebase.storage.reference.child(fileName)
        if (article.image.isNotBlank()) {
            storage.putFile(article.image.toUri()).addOnSuccessListener {
                storage.downloadUrl.addOnSuccessListener {
                    //set image link
                    article.image = it.toString()
                    //if image upload success ,then upload article
                    uploadArticle(article, onFailure)
                    onSuccess("Article has been uploaded")
                }
            }.removeOnFailureListener {
                onFailure("Fail to upload image !")
            }
        } else {
            //upload article without image
            uploadArticle(article, onFailure)
        }
    }


    private fun uploadArticle(
        article: ArticleEntity,
        onFail: (error: String) -> Unit
    ) {
        Firebase.firestore.collection(FIREBASE_COLLECTION).add(article)
            .addOnFailureListener {
                onFail("Fail to upload article !")
            }.addOnSuccessListener {
                it.update("id", it.id)
            }
    }
}