package com.example.aula1704.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula1704.database.AppDataBase
import com.example.aula1704.repository.DespesaRepository
import com.example.aula1704.repository.ViagemRepository

class ListDespesasViewModelFactory (val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDataBase.getDatabase(application).despesaDao()
        val despesaRepository = DespesaRepository(dao)
        return ListDespesasViewModel(despesaRepository) as T
    }
}