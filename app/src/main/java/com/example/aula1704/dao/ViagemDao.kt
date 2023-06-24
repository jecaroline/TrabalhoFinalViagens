package com.example.aula1704.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aula1704.entity.User
import com.example.aula1704.entity.Viagem

@Dao
interface ViagemDao {
    @Insert
    suspend fun insert(viagem: Viagem)

    @Update
    suspend fun update(viagem: Viagem)

    @Delete
    suspend fun delete(viagem: Viagem)

    @Query("select * from viagem v order by v.id")
    suspend fun findAll(): List<Viagem>

}