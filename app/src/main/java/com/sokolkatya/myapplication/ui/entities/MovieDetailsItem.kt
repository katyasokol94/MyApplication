package com.sokolkatya.myapplication.ui.entities

import com.sokolkatya.myapplication.data.entities.api.Genre

data class MovieDetailsItem(
    val minimumAge: Int,
    val backdropPath: String,
    val genres: List<Genre>,
    val id: Long,
    val overview: String,
    val posterPath: String,
    val runtime: Long,
    val title: String,
    val voteAverage: Double,
    val voteCount: Long,
    val actors: List<Actor>
)
