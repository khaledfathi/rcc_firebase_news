package com.kinsideapp.rcc_firebase_news.features.add_article.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.kinsideapp.rcc_firebase_news.R
import com.kinsideapp.rcc_firebase_news.core.global.BaseActivity
import com.kinsideapp.rcc_firebase_news.databinding.ActivityAddArticleBinding
import com.kinsideapp.rcc_firebase_news.features.add_article.presentation.viewmodel.AddArticleActivityViewModel

class AddArticleActivity : BaseActivity() {
    private lateinit var _binding: ActivityAddArticleBinding
    private lateinit var _viewModel: AddArticleActivityViewModel
    private var _imageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAddArticleBinding.inflate(layoutInflater)
        _viewModel = ViewModelProvider(this)[AddArticleActivityViewModel::class.java]
        enableEdgeToEdge()
        setContentView(_binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(_binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        init()
    }


    private fun init() {
        setEvents()
    }

    private fun setEvents() {
        eventImageButtonClick()
        eventSendButtonClick()
    }

    private fun eventImageButtonClick() {
        val handleReceiveImage =
            registerForActivityResult(ActivityResultContracts.GetContent()) { imageUri ->
                if (imageUri != null) {
                    _imageUri = imageUri
                    _binding.imageButton.setImageURI(_imageUri)
                } else {
                    _binding.imageButton.setImageResource(R.drawable.default_image)
                }
            }
        //event
        _binding.imageButton.setOnClickListener {
            handleReceiveImage.launch("image/*")
        }
    }

    private fun eventSendButtonClick() {
        _binding.sendButton.setOnClickListener {
            _viewModel.addArticle(
                _binding.title.text.toString(),
                _binding.article.text.toString(),
                _imageUri?.toString()?:""
            )
        }
    }

}