package com.example.assessment.model.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonResponseDto(
    val results: List<PersonDto>
)