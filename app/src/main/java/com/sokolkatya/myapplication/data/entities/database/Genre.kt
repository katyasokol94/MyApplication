package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class Genre(
    @PrimaryKey
    @ColumnInfo(name = "genre_id") var genreId: Int,
    @ColumnInfo(name = "name") var name: String
)
