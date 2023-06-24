package com.example.aula1704.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aula1704.entity.Despesa


@Dao
interface DespesaDao {

    @Insert
    suspend fun insert(despesa: Despesa)

    @Update
    suspend fun update(despesa: Despesa)

    @Delete
    suspend fun delete(despesa: Despesa)

    @Query("select * from despesa d order by d.data")
    suspend fun findAll(): List<Despesa>

    @Query("select * from despesa d where d.viagem = :viagem")
    suspend fun findByViagem(viagem: Int): Despesa?
}