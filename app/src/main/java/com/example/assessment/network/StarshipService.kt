package com.example.assessment.network

import com.example.assessment.model.dto.StarshipResponseDto
import retrofit2.http.GET

interface StarshipService {

    @GET("starships/")
    suspend fun fetchStarshipList(): StarshipResponseDto

}