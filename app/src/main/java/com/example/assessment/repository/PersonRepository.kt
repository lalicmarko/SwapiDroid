package com.example.assessment.repository

import androidx.annotation.WorkerThread
import com.example.assessment.model.entity.Person
import com.example.assessment.network.PersonClient
import com.example.assessment.persistance.PersonDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val peopleClient: PersonClient,
    private val personDao: PersonDao
) : Repository {

    @WorkerThread
    override fun getAll() = flow<List<Person>> {
        // here is the place for implementing custom stale logic when fetching people

        val localData = personDao.getAll()

        if (localData.isNullOrEmpty()) {
            // grabbing fresh remote data
            val response = peopleClient.fetchPeople()
            if (response.results.isNullOrEmpty()) {
                emit(emptyList())
            } else {
                response.results.map {
                    Person(null, name = it.name, it.mass, it.height)
                }.let {
                    // inserting and then emitting local data
                    personDao.insertPeople(it)
                    emit(personDao.getAll())
                }
            }
        } else {
            // emitting local data
            emit(localData)
        }
    }

    override suspend fun deleteAll() {
        personDao.deleteAll()
    }
}