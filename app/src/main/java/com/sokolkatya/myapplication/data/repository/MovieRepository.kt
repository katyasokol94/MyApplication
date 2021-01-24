package com.sokolkatya.myapplication.data.repository

import com.sokolkatya.myapplication.data.entities.Config
import com.sokolkatya.myapplication.data.entities.GenreList
import com.sokolkatya.myapplication.data.entities.MovieDetails
import com.sokolkatya.myapplication.data.entities.Popular
import com.sokolkatya.myapplication.module.RetrofitModule

class MovieRepository {

    private val retrofitModule: RetrofitModule = RetrofitModule()

    suspend fun loadConfig(): Config =
            retrofitModule
                    .serverApi
                    .loadConfig()

    suspend fun loadGenre(): GenreList =
            retrofitModule
                    .serverApi
                    .loadGenre()

    suspend fun loadPopular(): Popular =
            retrofitModule
                    .serverApi
                    .loadPopular()

    suspend fun loadMovieDetails(id: Int): MovieDetails =
            retrofitModule
                    .serverApi
                    .loadMovieDetails(movieId = id)
}