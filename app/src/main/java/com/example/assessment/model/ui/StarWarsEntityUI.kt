package com.example.assessment.model.ui

interface StarWarsEntityUI {
    fun getTitle(): String

    // default implementation
    fun getType(): String = javaClass.simpleName

    fun getStarWarsEntityId(): Long

    fun getShortInfo(): String
}