package com.example.assessment.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessment.model.entity.Person
import com.example.assessment.model.entity.Starship

@Database(entities = [Person::class, Starship::class], version = 4, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
    abstract fun starshipDao(): StarshipDao
}