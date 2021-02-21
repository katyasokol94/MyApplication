package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "movie_actor", primaryKeys = ["movie_id", "actor_id"])
data class MovieActor(
    @ColumnInfo(name = "movie_id") var movieId: Int,
    @ColumnInfo(name = "actor_id") var actorId: Int
)
