package com.example.nasapictureofdaymvvm.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictureofdaymvvm.data.ApodResponse
import com.example.nasapictureofdaymvvm.domain.usecase.GetApodUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ApodViewModel @Inject constructor(
    private val getApodUseCase: GetApodUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<ApodUiState>(ApodUiState.Loading)
    val uiState: StateFlow<ApodUiState> get() = _uiState

    init {
        loadApod()
    }

    private fun loadApod() {
        _uiState.value = ApodUiState.Loading
        getApodUseCase().onEach { result: Result<ApodResponse> ->
            result.fold(onSuccess = { response ->
                _uiState.value = ApodUiState.Success(response)
            }, onFailure = { exception ->
                _uiState.value = ApodUiState.Error(exception.message ?: "Unknown error")
            })
        }.launchIn(viewModelScope)
    }
}

sealed class ApodUiState {
    object Loading : ApodUiState()
    data class Success(val data: ApodResponse) : ApodUiState()
    data class Error(val message: String) : ApodUiState()
}