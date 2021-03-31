package com.example.assessment.network

import com.example.assessment.model.dto.PersonResponseDto
import javax.inject.Inject

class PeopleClient @Inject constructor(
    private val peopleService: PeopleService
) {

    suspend fun fetchPeople(): PersonResponseDto {
        return peopleService.fetchPersonList()
    }
}