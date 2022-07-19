package com.example.myapplication.data.room.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.example.myapplication.model.movie.Genre


class GenresConverter {
    @TypeConverter
    fun listToJsonString(value: List<Genre>?): String = Gson().toJson(value)

    @TypeConverter
    fun jsonStringToList(value: String) = Gson().fromJson(value, Array<Genre>::class.java).toList()
}