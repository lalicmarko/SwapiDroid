package com.example.assessment.persistance

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.assessment.model.entity.Person

@Database(entities = [Person::class], version = 2, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personDao(): PersonDao
}