package com.example.aula1704.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1704.entity.Despesa
import com.example.aula1704.entity.Viagem
import com.example.aula1704.repository.DespesaRepository
import com.example.aula1704.repository.ViagemRepository
import kotlinx.coroutines.launch

class ListDespesasViewModel (private val despesaRepository: DespesaRepository): ViewModel() {
    var despesas: MutableState<List<Despesa>> = mutableStateOf(listOf())

    var showDialogDelete = mutableStateOf(false)

    var despesaForDelete: Despesa? by mutableStateOf(null)

    fun buscarDespesas(){
        viewModelScope.launch {
            despesas.value = despesaRepository.finddAllDespesas(1)
        }
    }

    fun deleteDespesa() {
        viewModelScope.launch {
            despesaForDelete?.let {
                despesaRepository.delete(it)
                buscarDespesas()
            }
        }
    }

}