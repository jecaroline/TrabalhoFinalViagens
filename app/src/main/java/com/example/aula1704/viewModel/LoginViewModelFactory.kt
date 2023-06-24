package com.example.aula1704.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aula1704.database.AppDataBase
import com.example.aula1704.repository.UserRepository

class LoginViewModelFactory(val application: Application) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = AppDataBase.getDatabase(application).userDao()
        val userRepository = UserRepository(dao)
        return LoginViewModel(userRepository) as T
    }

}