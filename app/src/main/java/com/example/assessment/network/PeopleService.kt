package com.example.assessment.network

import com.example.assessment.model.dto.PersonResponseDto
import retrofit2.http.GET

interface PeopleService {

    @GET("people/")
    suspend fun fetchPersonList(): PersonResponseDto

}