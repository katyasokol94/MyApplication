package com.sokolkatya.myapplication.data.entities.database

import com.sokolkatya.myapplication.data.entities.api.Genre
import com.sokolkatya.myapplication.ui.entities.Actor

data class DBMovieDetailsItem(
    val minimumAge: Int,
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Long,
    val overview: String,
    val runtime: Long,
    val title: String,
    val voteAverage: Double,
    val voteCount: Long,
    val actors: List<Actor>
)
