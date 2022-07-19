package com.example.myapplication.data.repository.impl

import com.example.myapplication.data.repository.AgendaRepository
import com.example.myapplication.data.room.AppDb
import com.example.myapplication.model.agenda.Contacto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AgendaRepositoryImpl @Inject constructor(
    private val db: AppDb,
) :AgendaRepository {

    override suspend fun insertContacto(contacto: Contacto) {
        db.agendaDao.insert(contacto)
    }

    override suspend fun updateContacto(contacto: Contacto) {
        db.agendaDao.update(contacto)
    }

    override suspend fun deleteContacto(contacto: Contacto) {
       db.agendaDao.delete(contacto)
    }

    override fun getContactos(): Flow<List<Contacto>> = flow {
         emit(db.agendaDao.findAll())
    }.flowOn(Dispatchers.IO)
}