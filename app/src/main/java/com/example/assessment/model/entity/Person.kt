package com.example.assessment.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assessment.model.ui.StarWarsEntityUI

/**
 * StarWars character entity
 */
@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val mass: String,
    val height: String
) : StarWarsEntityUI, BaseModelItem {

    override fun getTitle(): String {
        return name
    }

    override fun getShortInfo(): String {
        return "This ${getType()}'s name is $name, his weight is $mass kg, his height is $height cm."
    }

    override fun getStarWarsEntityId(): Long {
        return id ?: -1
    }
}
