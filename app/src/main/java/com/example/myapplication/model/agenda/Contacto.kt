package com.example.myapplication.model.agenda

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Contacto.TABLE_NAME )
data class Contacto(
    @PrimaryKey
    val celular: String,
    val nombre:String? = null,
    val apellidos:String? = null,
    val direccion:String? = null,
    val correo:String? = null
) {
    companion object{
        const val TABLE_NAME = "contacto"
    }

}

