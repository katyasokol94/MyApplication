package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "movie_actor")
data class MovieActor(
    @PrimaryKey(autoGenerate = true) val id: Int,

    @ForeignKey(entity = Movie::class, parentColumns = ["id"], childColumns = ["movie_id"])
    @ColumnInfo(name = "movie_id") var movieId: Int,

    @ForeignKey(entity = Actor::class, parentColumns = ["id"], childColumns = ["actor_id"])
    @ColumnInfo(name = "actor_id") var actorId: Int
)
