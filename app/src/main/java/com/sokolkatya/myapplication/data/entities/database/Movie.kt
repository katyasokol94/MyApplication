package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey
    @ColumnInfo(name = "movie_id") var movieId: Int? = null,
    @ColumnInfo(name = "minimum_age") var minimumAge: Int? = null,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String? = null,
    @ColumnInfo(name = "overview") var overview: String? = null,
    @ColumnInfo(name = "poster_path") var posterPath: String? = null,
    @ColumnInfo(name = "runtime") var runtime: Int? = null,
    @ColumnInfo(name = "title") var title: String? = null,
    @ColumnInfo(name = "vote_average") var voteAverage: Float? = null,
    @ColumnInfo(name = "vote_count") var voteCount: Int? = null,
    @ColumnInfo(name = "ratings") var ratings: Float? = null
)
