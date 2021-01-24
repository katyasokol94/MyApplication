package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Result (
    @SerialName("poster_path")
    val posterPath: String,
    val adult: Boolean,
    @SerialName("genre_ids")
    val genreIDS: List<Int>,
    val id: Long,
    val title: String,
    @SerialName("vote_count")
    val voteCount: Int,
    @SerialName("vote_average")
    val voteAverage: Double
)
