package com.example.assessment.persistance

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.assessment.model.entity.Starship

@Dao
interface StarshipDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarships(people: List<Starship>)

    @Query("SELECT * FROM starship")
    suspend fun getAll(): List<Starship>

    @Query("DELETE FROM starship")
    suspend fun deleteAll()
}