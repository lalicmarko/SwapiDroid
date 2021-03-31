package com.example.assessment.repository

import androidx.annotation.WorkerThread
import com.example.assessment.model.entity.Starship
import com.example.assessment.network.StarshipClient
import com.example.assessment.persistance.StarshipDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class StarshipRepository @Inject constructor(
    private val starshipClient: StarshipClient,
    private val starshipDao: StarshipDao
) : Repository {

    @WorkerThread
    override fun getAll() = flow<List<Starship>> {
        // here is the place for implementing custom stale logic when fetching starships

        val localData = starshipDao.getAll()

        if (localData.isNullOrEmpty()) {
            // grabbing fresh remote data
            val response = starshipClient.fetchStarships()
            if (response.results.isNullOrEmpty()) {
                emit(emptyList())
            } else {
                response.results.map {
                    Starship(null, name = it.name, it.model, it.manufacturer, it.starshipClass)
                }.let {
                    // inserting and then emitting local data
                    starshipDao.insertStarships(it)
                    emit(starshipDao.getAll())
                }
            }
        } else {
            // emitting local data
            emit(localData)
        }
    }

    override suspend fun deleteAll() {
        starshipDao.deleteAll()
    }
}