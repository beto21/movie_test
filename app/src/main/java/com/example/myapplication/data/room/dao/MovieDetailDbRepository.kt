package com.example.myapplication.data.room.dao

import com.example.myapplication.model.movie.MovieDetail

interface MovieDetailDbRepository {

    suspend fun findMovieById(id: Int): MovieDetail?

    suspend fun insertMovie(movie: MovieDetail)

    suspend fun insertMovies(movies: List<MovieDetail>)

    suspend fun findMovies(): List<MovieDetail>

    suspend fun updateMovie(movie: MovieDetail)

    suspend fun deleteMovie(movie: MovieDetail)

    suspend fun deleteMovies(movies: List<MovieDetail>)

}