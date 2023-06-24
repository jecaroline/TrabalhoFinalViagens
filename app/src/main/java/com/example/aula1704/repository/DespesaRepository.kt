package com.example.aula1704.repository

import com.example.aula1704.dao.DespesaDao
import com.example.aula1704.dao.UserDao
import com.example.aula1704.entity.Despesa
import com.example.aula1704.entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DespesaRepository(private val despesaDao: DespesaDao) {


    private val coroutine = CoroutineScope(Dispatchers.Main)

    fun addDespesa(despesa: Despesa) {
        coroutine.launch(Dispatchers.IO) {
            despesaDao.insert(despesa)
        }
    }

    fun delete(despesa: Despesa) {
        coroutine.launch(Dispatchers.IO) {
            despesaDao.delete(despesa)
        }
    }

    suspend fun finddAllDespesas(viagem: Int): List<Despesa> {
        return despesaDao.findAll()
    }

    suspend fun findByViagem(viagem: Int): Despesa? =
        despesaDao.findByViagem(viagem)
}