package com.example.aula1704.repository

import com.example.aula1704.dao.ViagemDao
import com.example.aula1704.entity.Viagem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViagemRepository (private val viagemDao: ViagemDao) {


    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addViagem(viagem: Viagem) {
        coroutine.launch(Dispatchers.IO) {
            viagemDao.insert(viagem)
        }
    }

    fun delete(viagem: Viagem) {
        coroutine.launch(Dispatchers.IO) {
            viagemDao.delete(viagem)
        }
    }

    suspend fun buscarViagens(): List<Viagem> {
        return viagemDao.findAll()
    }
}