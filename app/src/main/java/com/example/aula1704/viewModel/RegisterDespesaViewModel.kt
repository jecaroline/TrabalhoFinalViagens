package com.example.aula1704.viewModel;

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1704.entity.Despesa
import com.example.aula1704.repository.DespesaRepository;
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterDespesaViewModel(private val despesaRepository:DespesaRepository): ViewModel() {

    var data by mutableStateOf("")
    var valor by mutableStateOf("")
    var tipo by mutableStateOf("")
    var descricao by mutableStateOf("")
    var viagem by mutableStateOf(0)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    fun registrar(onSuccess: () -> Unit) {
        try {
            val newDespesa = Despesa(data = data, valor = valor, tipo = tipo, descricao = descricao, viagem = viagem)
            despesaRepository.addDespesa(newDespesa)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }
    }
}
