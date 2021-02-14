package com.sokolkatya.myapplication.data.sourse.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sokolkatya.myapplication.data.entities.database.Actor
import com.sokolkatya.myapplication.data.entities.database.DBGenre
import com.sokolkatya.myapplication.data.entities.database.DBMovieItem
import com.sokolkatya.myapplication.data.entities.database.Genre
import com.sokolkatya.myapplication.data.entities.database.Movie
import com.sokolkatya.myapplication.data.entities.database.MovieActor
import com.sokolkatya.myapplication.data.entities.database.MovieGenre

@Dao
interface AppDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<Movie>)

    @Query("SELECT movie_id,title,poster_path,vote_average,vote_count,minimum_age FROM movie")
    fun getMovies(): List<DBMovieItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGenres(genres: List<Genre>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMovieGenres(movieGenres: List<MovieGenre>)

    @Query("SELECT movie_genre.movie_id, genre.genre_id, genre.name FROM movie_genre, genre  WHERE movie_genre.genre_id = genre.genre_id")
    fun getMovieGenres(): List<DBGenre>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertActors(actors: List<Actor>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieActors(movieActors: List<MovieActor>)
}