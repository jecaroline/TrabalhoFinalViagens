package com.example.aula1704.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula1704.database.AppDataBase
import com.example.aula1704.repository.ViagemRepository

class ListViagensViewModelFactory (val application: Application) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDataBase.getDatabase(application).viagemDao()
        val viagemRepository = ViagemRepository(dao)
        return ListViagensViewModel(viagemRepository) as T
    }
}