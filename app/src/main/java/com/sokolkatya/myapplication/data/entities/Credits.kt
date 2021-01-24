package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val cast: List<Actor>
)
