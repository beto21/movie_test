package com.example.myapplication.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.myapplication.model.agenda.Contacto

@Dao
interface AgendaDao : BaseDao<Contacto> {

    @Transaction
    @Query("SELECT * FROM contacto ")
    suspend fun findAll(): List<Contacto>

    @Transaction
    @Query("SELECT * FROM contacto WHERE celular = :celular LIMIT 1")
    suspend fun findById(celular: String): Contacto?
}