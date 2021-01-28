package com.sokolkatya.myapplication.data.entities.api

import kotlinx.serialization.Serializable

@Serializable
data class Genre(
    val id: Int,
    val name: String
)