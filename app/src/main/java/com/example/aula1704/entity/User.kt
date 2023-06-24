package com.example.aula1704.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")

data class User (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val novoNome    : String,
    val novoUsuario : String,
    val novaSenha   : String
){

}