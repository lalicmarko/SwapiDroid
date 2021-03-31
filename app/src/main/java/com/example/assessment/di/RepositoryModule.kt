package com.example.assessment.di

import com.example.assessment.network.PeopleClient
import com.example.assessment.persistance.PersonDao
import com.example.assessment.repository.PersonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        peopleClient: PeopleClient,
        personDao: PersonDao
    ): PersonRepository {
        return PersonRepository(peopleClient, personDao)
    }

    // here other repository can be provided
}