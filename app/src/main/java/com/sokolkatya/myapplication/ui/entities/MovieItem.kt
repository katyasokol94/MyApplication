package com.sokolkatya.myapplication.ui.entities

import com.sokolkatya.myapplication.data.entities.api.Genre

data class MovieItem(
    val id: Int,
    val title: String,
    val poster: String,
    val ratings: Float,
    val numberOfRatings: Int,
    val minimumAge: Int,
    val genres: List<Genre>,
)
