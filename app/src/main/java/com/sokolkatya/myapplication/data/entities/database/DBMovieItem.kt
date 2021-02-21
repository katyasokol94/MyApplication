package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo

data class DBMovieItem(
    @ColumnInfo(name = "movie_id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster_path") val poster: String,
    @ColumnInfo(name = "vote_average") val ratings: Float,
    @ColumnInfo(name = "vote_count") val numberOfRatings: Int,
    @ColumnInfo(name = "minimum_age") val minimumAge: Int
)