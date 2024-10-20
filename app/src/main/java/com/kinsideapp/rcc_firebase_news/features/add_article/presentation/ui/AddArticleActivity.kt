package com.kinsideapp.rcc_firebase_news.features.add_article.presentation.ui

import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.kinsideapp.rcc_firebase_news.R
import com.kinsideapp.rcc_firebase_news.databinding.ActivityAddArticleBinding
import com.kinsideapp.rcc_firebase_news.features.add_article.presentation.viewmodel.AddArticleActivityViewModel
import com.metro_driver.core.BaseActivity
import kotlinx.coroutines.launch

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

    /***** CORE *****/
    private fun init() {
        resizeOnKeyboardOpen(_binding.main)
        setEvents()
    }

    private fun setEvents() {
        eventFormHolderClick()
        eventNavigationButtonClick()
        eventImageButtonClick()
        eventSendButtonClick()
    }
    /***** -END- CORE *****/

    /***** EVENTS *****/
    private fun eventFormHolderClick() {
        _binding.formHolder.setOnClickListener {
            actionClearInputsFoucs()
            hideKeyboard(_binding.formHolder)
        }
    }

    private fun eventNavigationButtonClick() {
        _binding.toolBar.setNavigationOnClickListener { finish() }
    }

    private fun eventImageButtonClick() {
        //pick image
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
            actionClearInputsFoucs()
            handleReceiveImage.launch("image/*")
        }
    }

    private fun eventSendButtonClick() {
        val saveButton = _binding.toolBar.menu[0]
        saveButton.setOnMenuItemClickListener {
            saveButton.isEnabled = false
            hideKeyboard(_binding.toolBar)
            _binding.progress.isVisible = true
            _viewModel.addArticle(
                title = _binding.title.text.toString(),
                article = _binding.article.text.toString(),
                image = _imageUri?.toString() ?: "",
                onSuccess = { successMessage ->
                    saveButton.isEnabled = true
                    _binding.progress.isVisible = false
                    showToastShort(successMessage)
                },
                onFailure = { error ->
                    saveButton.isEnabled = true
                    _binding.progress.isVisible = false
                    showToastShort(error)
                }
            )
            true
        }
    }
    /***** -END- EVENTS *****/

    /***** EVENT ACTIONS *****/
    private fun actionClearInputsFoucs() {
        if (_binding.title.isFocused) _binding.article.clearFocus()
        if (_binding.article.isFocused) _binding.article.clearFocus()
    }
    /***** -END- EVENT ACTIONS *****/

}