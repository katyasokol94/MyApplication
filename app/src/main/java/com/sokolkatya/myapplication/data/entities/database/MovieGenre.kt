package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie_genre")
data class MovieGenre(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ForeignKey(entity = Movie::class, parentColumns = ["id"], childColumns = ["movie_id"])
    @ColumnInfo(name = "movie_id") var movieId: Int,

    @ForeignKey(entity = Genre::class, parentColumns = ["id"], childColumns = ["genre_id"])
    @ColumnInfo(name = "genre_id") var genreId: Int
)
