package com.example.aula1704.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1704.entity.Viagem
import com.example.aula1704.repository.ViagemRepository
import kotlinx.coroutines.launch

class ListViagensViewModel(private val viagemRepository: ViagemRepository): ViewModel() {
    var viagens: MutableState<List<Viagem>> = mutableStateOf(listOf())

    var showDialogDelete = mutableStateOf(false)

    var viagemForDelete: Viagem? by mutableStateOf(null)

    fun buscarViagens(){
        viewModelScope.launch {
            viagens.value = viagemRepository.buscarViagens()
        }
    }

    fun deleteViagem() {
        viewModelScope.launch {
            viagemForDelete?.let {
                viagemRepository.delete(it)
                buscarViagens()
            }
        }
    }

}

