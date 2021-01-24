package com.sokolkatya.myapplication.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("base_url")
    val baseURL: String
)
