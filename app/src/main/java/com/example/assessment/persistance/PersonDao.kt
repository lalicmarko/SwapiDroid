package com.example.assessment.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assessment.model.entity.Person

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPeople(people: List<Person>)

    @Query("SELECT * FROM person")
    suspend fun getAll(): List<Person>

    @Query("DELETE FROM person")
    suspend fun deleteAll()
}