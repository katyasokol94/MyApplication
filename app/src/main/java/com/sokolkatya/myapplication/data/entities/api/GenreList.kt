package com.sokolkatya.myapplication.data.entities.api

import kotlinx.serialization.Serializable

@Serializable
data class GenreList(
    val genres: List<Genre>
)
