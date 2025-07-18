package com.example.nasapictureofdaymvvm

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.bumptech.glide.Glide
import com.example.nasapictureofdaymvvm.data.ApodResponse
import com.example.nasapictureofdaymvvm.databinding.ActivityMainBinding
import com.example.nasapictureofdaymvvm.presentation.viewmodel.ApodUiState
import com.example.nasapictureofdaymvvm.presentation.viewmodel.ApodViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ApodViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupObservers()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    handleUiState(state)
                }
            }
        }
    }

    private fun handleUiState(state: ApodUiState) {
        when (state) {
            is ApodUiState.Loading -> showLoading()
            is ApodUiState.Success -> showContent(state.data)
            is ApodUiState.Error -> showError(state.message)
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.contentScrollView.visibility = View.GONE
        binding.errorMessage.visibility = View.GONE
    }

    private fun showContent(apod: ApodResponse) {
        binding.progressBar.visibility = View.GONE
        binding.contentScrollView.visibility = View.VISIBLE
        binding.errorMessage.visibility = View.GONE

        binding.titleTextView.text = apod.title
        binding.dateTextView.text = apod.date
        binding.explanationTextView.text = apod.explanation

        binding.copyrightTextView.apply {
            visibility = if (apod.copyright != null) {
                text = "Copyright: ${apod.copyright}"
                View.VISIBLE
            } else {
                View.GONE
            }
        }

        binding.apodImageView.visibility = if (apod.mediaType == "image") {
            Glide.with(this).load(apod.url).into(binding.apodImageView)
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.contentScrollView.visibility = View.GONE
        binding.errorMessage.apply {
            visibility = View.VISIBLE
            text = message
        }
    }
}