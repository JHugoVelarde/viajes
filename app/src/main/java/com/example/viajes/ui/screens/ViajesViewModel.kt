package com.example.viajes.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.viajes.data.model.Destino
import com.example.viajes.data.repository.DestinoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// Los 3 estados posibles de nuestra pantalla
sealed interface ViajesUiState {
    data object Loading : ViajesUiState
    data class Success(val destinos: List<Destino>) : ViajesUiState
    data class Error(val message: String) : ViajesUiState
}

@HiltViewModel
class ViajesViewModel @Inject constructor(
    private val repository: DestinoRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<ViajesUiState>(ViajesUiState.Loading)
    val uiState: StateFlow<ViajesUiState> = _uiState.asStateFlow()

    init {
        cargarDestinos()
    }

    private fun cargarDestinos() {
        viewModelScope.launch {
            _uiState.value = ViajesUiState.Loading // 1. Mostramos que está cargando
            try {
                val destinos = repository.getDestinos() // 2. Vamos a internet (simulado)
                _uiState.value = ViajesUiState.Success(destinos) // 3. Mostramos los datos
            } catch (e: Exception) {
                _uiState.value = ViajesUiState.Error("Error al cargar los destinos") // 4. Si algo falla
            }
        }
    }
}