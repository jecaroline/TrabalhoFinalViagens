package com.example.aula1704.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "despesa")

data class Despesa (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val data      : String,
    val valor     : String,
    val tipo      : String,
    val descricao : String,
    val viagem    : Int,
){

}