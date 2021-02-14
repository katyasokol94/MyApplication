package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_genre", primaryKeys = ["movie_id", "genre_id"])
data class MovieGenre(

    @ColumnInfo(name = "movie_id") var movieId: Int,
    @ColumnInfo(name = "genre_id") var genreId: Int
)

