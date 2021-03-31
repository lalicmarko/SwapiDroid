package com.example.assessment.network

import com.example.assessment.model.dto.PersonResponseDto
import javax.inject.Inject

class PersonClient @Inject constructor(
    private val personService: PersonService
) {

    suspend fun fetchPeople(): PersonResponseDto {
        return personService.fetchPersonList()
    }
}