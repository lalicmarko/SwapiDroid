package com.example.assessment.repository

import com.example.assessment.model.entity.BaseModelItem
import kotlinx.coroutines.flow.Flow

/**
 *  Interface for configuring repository classes
 */
interface Repository {
    fun getAll(): Flow<List<BaseModelItem>>
    suspend fun deleteAll()
}