package com.example.assessment.network

import com.example.assessment.model.dto.StarshipResponseDto
import javax.inject.Inject

class StarshipClient @Inject constructor(
    private val starshipService: StarshipService
) {

    suspend fun fetchStarships(): StarshipResponseDto {
        return starshipService.fetchStarshipList()
    }
}