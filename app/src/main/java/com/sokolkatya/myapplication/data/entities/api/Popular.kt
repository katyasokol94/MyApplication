package com.sokolkatya.myapplication.data.entities.api

import kotlinx.serialization.Serializable

@Serializable
data class Popular(
    val results: List<Result>,
)
