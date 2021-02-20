package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.ui.entities.Actor

data class DBMovieDetailsItem(
    @ColumnInfo(name = "movie_id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "backdrop_path") val backdropPath: String,
    @ColumnInfo(name = "vote_average") val voteAverage: Float,
    @ColumnInfo(name = "vote_count") val voteCount: Int,
    @ColumnInfo(name = "minimum_age") val minimumAge: Int,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "runtime") var runtime: Int,
)
