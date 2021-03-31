package com.example.assessment.di

import com.example.assessment.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://swapi.dev/api/"

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpRequestInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePeopleService(retrofit: Retrofit): PersonService {
        return retrofit.create(PersonService::class.java)
    }

    @Provides
    @Singleton
    fun provideStarshipService(retrofit: Retrofit): StarshipService {
        return retrofit.create(StarshipService::class.java)
    }

    @Provides
    @Singleton
    fun providePeopleClient(service: PersonService): PersonClient {
        return PersonClient(service)
    }

    @Provides
    @Singleton
    fun provideStarshipClient(service: StarshipService): StarshipClient {
        return StarshipClient(service)
    }
}