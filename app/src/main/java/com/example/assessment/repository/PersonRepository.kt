package com.example.assessment.repository

import android.util.Log
import androidx.annotation.WorkerThread
import com.example.assessment.model.entity.Person
import com.example.assessment.network.PeopleClient
import com.example.assessment.persistance.PersonDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val peopleClient: PeopleClient,
    private val personDao: PersonDao
) : Repository {

    @WorkerThread
    override fun getAll() = flow<List<Person>> {
        val localMovies = personDao.getAll()
        if (localMovies.isNullOrEmpty()) {
            val response = peopleClient.fetchPeople()
            if (response.results.isNullOrEmpty()) {
                Log.i("BOBAN", "empty response")
                emit(emptyList())
            } else {
                response.results.map {
                    Person(null, name = it.name)
                }.let {
                    personDao.insertPeople(it)
                    emit(personDao.getAll())
                }
            }
        } else {
            Log.i("BOBAN", "returned from local")
            emit(localMovies)
        }
    }

    override suspend fun deleteAll() {
        personDao.deleteAll()
    }
}