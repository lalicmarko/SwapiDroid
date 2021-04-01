package com.example.assessment.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assessment.model.ui.StarWarsEntityUI

/**
 * StarWars starship entity
 */
@Entity
data class Starship(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String,
    val model: String,
    val manufacturer: String,
    val starshipClass: String
) : StarWarsEntityUI, BaseModelItem {

    override fun getTitle(): String {
        return name
    }

    override fun getShortInfo(): String {
        return "This is a ${getType()}, manufactured by $manufacturer, model name: $model, class = $starshipClass"
    }

    override fun getStarWarsEntityId(): Long {
        return id ?: -1
    }
}
