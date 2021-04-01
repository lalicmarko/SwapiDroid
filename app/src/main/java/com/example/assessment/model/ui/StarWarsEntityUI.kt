package com.example.assessment.model.ui

/**
 * Describes UI behavior of models implementing this interface.
 *
 * All models implementing this interface can be shown in [StarWarsAdapter]
 *
 */
interface StarWarsEntityUI {
    fun getTitle(): String

    // default implementation
    fun getType(): String = javaClass.simpleName

    fun getStarWarsEntityId(): Long

    fun getShortInfo(): String
}