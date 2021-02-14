package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo

data class DBGenre(
    @ColumnInfo(name = "movie_id") var movieId: Int,
    @ColumnInfo(name = "genre_id") var genreId: Int,
    @ColumnInfo(name = "name") var name: String
)
