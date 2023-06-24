package com.example.aula1704.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viagem")

data class Viagem (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val destino    : String,
    val dataInicio : String,
    val dataFim    : String,
    val orcamento  : String
){

}