package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class GenreList(
    val genres: List<Genre>
)
