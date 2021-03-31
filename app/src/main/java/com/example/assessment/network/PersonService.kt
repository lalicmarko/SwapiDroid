package com.example.assessment.network

import com.example.assessment.model.dto.PersonResponseDto
import retrofit2.http.GET

interface PersonService {

    @GET("people/")
    suspend fun fetchPersonList(): PersonResponseDto

}