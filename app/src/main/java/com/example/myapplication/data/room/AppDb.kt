package com.example.myapplication.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapplication.data.room.converter.GenresConverter
import com.example.myapplication.data.room.dao.*
import com.example.myapplication.model.agenda.Contacto
import com.example.myapplication.model.movie.Movie
import com.example.myapplication.model.movie.MovieDetail
import com.example.myapplication.model.movie.MoviePlayNow

@Database(
    entities =
    [Movie::class,
        MovieDetail::class,
        MoviePlayNow::class,
    Contacto::class], version = 1, exportSchema = false
)

@TypeConverters(
    GenresConverter::class
)
abstract class AppDb :RoomDatabase(){
    abstract val movieDao: MovieDao
    abstract val movieDetailDao: MovieDetailDao
    abstract val movieAndDetailDao: MovieAndDetailDao
    abstract val moviePlayNowDao:MoviePlayNowDao
    abstract val agendaDao:AgendaDao
}