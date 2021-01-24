package com.sokolkatya.myapplication.data.sourse.server

import com.sokolkatya.myapplication.data.entities.Config
import com.sokolkatya.myapplication.data.entities.GenreList
import com.sokolkatya.myapplication.data.entities.MovieDetails
import com.sokolkatya.myapplication.data.entities.Popular
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @GET("movie/popular")
    suspend fun loadPopular(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
    ): Popular

    @GET("genre/movie/list")
    suspend fun loadGenre(
        @Query("language") language: String = "en-US"
    ): GenreList

    @GET("movie/{movie_id}")
    suspend fun loadMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("append_to_response") appendToResponse: String = "credits"
    ): MovieDetails

    @GET("configuration")
    suspend fun loadConfig(): Config
}