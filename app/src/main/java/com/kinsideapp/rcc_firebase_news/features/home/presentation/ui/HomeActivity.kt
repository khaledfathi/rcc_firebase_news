package com.kinsideapp.rcc_firebase_news.features.home.presentation.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kinsideapp.rcc_firebase_news.core.RouteToAddArticle
import com.kinsideapp.rcc_firebase_news.core.RouteToNews
import com.kinsideapp.rcc_firebase_news.core.RouteToScreen
import com.kinsideapp.rcc_firebase_news.databinding.ActivityHomeBinding
import com.metro_driver.core.BaseActivity

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
        eventGoToTestScreen()
    }

    private fun eventNewsButtonClick() {
        _binding.newsButton.setOnClickListener { RouteToNews(this).navigate() }
    }

    private fun eventAddArticleButtonClick() {
        _binding.addArticleButton.setOnClickListener { RouteToAddArticle(this).navigate() }
    }

    private fun eventGoToTestScreen() {
        _binding.testButton.setOnClickListener {
            RouteToScreen(this).navigate()
        }
    }

}