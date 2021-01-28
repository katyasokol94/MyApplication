package com.sokolkatya.myapplication.data.entities.api

import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val cast: List<Cast>
)
