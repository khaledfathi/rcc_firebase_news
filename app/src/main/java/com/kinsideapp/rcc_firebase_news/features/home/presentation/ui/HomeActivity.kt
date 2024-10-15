package com.kinsideapp.rcc_firebase_news.features.home.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kinsideapp.rcc_firebase_news.core.global.BaseActivity
import com.kinsideapp.rcc_firebase_news.core.route.RouteToAddArticle
import com.kinsideapp.rcc_firebase_news.core.route.RouteToNews
import com.kinsideapp.rcc_firebase_news.databinding.ActivityHomeBinding

class HomeActivity : BaseActivity() {
    private lateinit var _binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        setEvents()
    }

    private fun setEvents() {
        eventAddArticleButtonClick()
        eventNewsButtonClick()
    }

    private fun eventNewsButtonClick() {
        _binding.newsButton.setOnClickListener { RouteToNews(this).navigate() }
    }

    private fun eventAddArticleButtonClick() {
        _binding.addArticleButton.setOnClickListener { RouteToAddArticle(this).navigate() }
    }

}