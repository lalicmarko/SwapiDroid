package com.example.assessment.model.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarshipDto(
    val name: String,
    val model: String,
    val manufacturer: String,
    @field:Json(name = "starship_class") val starshipClass: String
)