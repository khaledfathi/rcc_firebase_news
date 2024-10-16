package com.kinsideapp.rcc_firebase_news.features.add_article.data.repository

import androidx.core.net.toUri
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.kinsideapp.rcc_firebase_news.core.global.FIREBASE_COLLECTION
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.entity.ArticleEntity
import com.kinsideapp.rcc_firebase_news.features.add_article.domain.repository.AddArticleRepository
import java.util.Calendar

class AddArticleRepositoryImp : AddArticleRepository {

    override fun addNewArticle(
        article: ArticleEntity,
        onSuccess: (message: String) -> Unit,
        onFailure: (error: String) -> Unit
    ) {
        //create image name
        val c = Calendar.getInstance()
        val y = c.get(Calendar.YEAR)
        val M = c.get(Calendar.MONTH) + 1
        val d = c.get(Calendar.DAY_OF_MONTH)
        val h = c.get(Calendar.HOUR_OF_DAY)
        val m = c.get(Calendar.MINUTE)
        val s = c.get(Calendar.SECOND)
        val mil = c.get(Calendar.MILLISECOND)
        val name = "$d-$M-$y $h:$m:$s:$mil"

        //upload image
        val storage = Firebase.storage.reference.child(name)

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
            }
    }
}