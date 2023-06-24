package com.example.aula1704.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1704.entity.Viagem
import com.example.aula1704.repository.ViagemRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterViagemViewModel (private val viagemRepository: ViagemRepository): ViewModel() {

    var destino by mutableStateOf("")
    var dataInicio by mutableStateOf("")
    var dataFim by mutableStateOf("")
    var orcamento by mutableStateOf("")

    var isDataInicioValid by mutableStateOf(true)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validateFields() {
        isDataInicioValid = dataInicio.isNotEmpty()
        if (!isDataInicioValid) {
            throw Exception("Data de início is required")
        }
    }

    fun registrar(onSuccess: () -> Unit) {
        try {
            validateFields()
            val novaViagem = Viagem(destino = destino, dataInicio = dataInicio, dataFim = dataFim, orcamento = orcamento)
            viagemRepository.addViagem(novaViagem)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }
    }
}