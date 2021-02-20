package com.sokolkatya.myapplication.data.entities.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String,

    val genres: List<Genre>,
    val id: Long,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String,

    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    val title: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long,

    val credits: Credits
)
