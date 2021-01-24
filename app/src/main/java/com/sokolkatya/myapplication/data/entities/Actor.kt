package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Actor(
    val id: Int,
    val name: String,
    @SerialName("profile_path")
    val picture: String
)