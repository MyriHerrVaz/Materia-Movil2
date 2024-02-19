package com.example.accesosicenet.screens
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface MarsUiState {
    data class Success(val usuario: String) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}
class ApiViewModel(): ViewModel() {
}
