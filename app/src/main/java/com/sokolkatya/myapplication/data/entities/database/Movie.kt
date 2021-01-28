package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie")
data class Movie(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "movie_id") var movieId: Int,
    @ColumnInfo(name = "minimum_age") var minimumAge: Int,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "poster_path") var posterPath: String,
    @ColumnInfo(name = "runtime") var runtime: Int,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "vote_average") var voteAverage: Float,
    @ColumnInfo(name = "vote_count") var voteCount: Int,
    @ColumnInfo(name = "ratings") var ratings: Float,
    @ColumnInfo(name = "brand_id") var numberOfRatings: Int,
)
