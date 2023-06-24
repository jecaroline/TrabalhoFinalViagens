package com.example.aula1704.viewModel

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aula1704.entity.User
import com.example.aula1704.repository.UserRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class RegisterNewUserViewModel(private val userRepository: UserRepository): ViewModel() {

    var novoNome by mutableStateOf("")
    var novoUsuario by mutableStateOf("")
    var novaSenha by mutableStateOf("")

    var isNameValid by mutableStateOf(true)

    private val _toastMessage = MutableSharedFlow<String>()
    val toastMessage = _toastMessage.asSharedFlow()

    private fun validateFields() {
        isNameValid = novoNome.isNotEmpty()
        if (!isNameValid) {
            throw Exception("Name is required")
        }
    }

    fun registrar(onSuccess: () -> Unit) {
        try {
            validateFields()
            val newUser = User(novoNome = novoNome, novoUsuario = novoUsuario, novaSenha = novaSenha)
            userRepository.addUser(newUser)
            onSuccess()
        }
        catch (e: Exception) {
            viewModelScope.launch {
                _toastMessage.emit(e.message?: "Unknown error")
            }
        }
    }
}