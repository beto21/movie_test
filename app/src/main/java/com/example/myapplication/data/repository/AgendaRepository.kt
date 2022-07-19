package com.example.myapplication.data.repository

import com.example.myapplication.model.agenda.Contacto
import kotlinx.coroutines.flow.Flow

interface AgendaRepository {

    suspend fun insertContacto(contacto: Contacto)

    suspend fun updateContacto(contacto: Contacto)

    suspend fun deleteContacto(contacto: Contacto)

    fun getContactos(): Flow<List<Contacto>>


}