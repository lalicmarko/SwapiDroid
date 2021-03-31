package com.example.assessment.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarshipResponseDto(
    val results: List<StarshipDto>
)