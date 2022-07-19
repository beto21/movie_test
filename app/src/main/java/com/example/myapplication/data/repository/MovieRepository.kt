package com.example.myapplication.data.repository

import androidx.paging.PagingData
import com.example.myapplication.model.movie.Movie
import com.example.myapplication.model.movie.MoviePlayNow
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getMoviePopular(): Flow<List<Movie>>

    fun getMoviePopularPage(): Flow<PagingData<Movie>>

    fun getMoviePlayNow(): Flow<List<MoviePlayNow>>

    fun findMovieById(id: Int): Flow<Movie>

    suspend fun insertMovies(movies: List<Movie>)

    suspend fun insertMovie(movie: Movie)

    suspend fun deleteMovie(movie: Movie)

    suspend fun deleteMovies(movies: List<Movie>)

    fun findMovieAndDetailById(id: Int): Flow<Movie>


}