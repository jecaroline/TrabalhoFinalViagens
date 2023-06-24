package com.example.aula1704.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.aula1704.entity.User

@Dao
interface UserDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("select * from user u order by u.novoNome")
    suspend fun findAll(): List<User>

    @Query("select * from user u where u.novoUsuario = :name")
    suspend fun findByName(name: String): User?
}