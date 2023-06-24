package com.example.aula1704.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.aula1704.dao.DespesaDao
import com.example.aula1704.dao.UserDao
import com.example.aula1704.dao.ViagemDao
import com.example.aula1704.entity.Despesa
import com.example.aula1704.entity.User
import com.example.aula1704.entity.Viagem

@Database(entities =[User::class, Viagem::class, Despesa::class], version = 1 )
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDao() : UserDao
    abstract fun viagemDao() : ViagemDao
    abstract fun despesaDao() : DespesaDao


    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(application: Application): AppDataBase = INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                application,
                AppDataBase ::class.java,
                "meu-db-4"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

