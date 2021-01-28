package com.sokolkatya.myapplication.data.entities.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actor")
data class Actor(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "actor_id") var actorId: Int,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "picture") var picture: String
)
