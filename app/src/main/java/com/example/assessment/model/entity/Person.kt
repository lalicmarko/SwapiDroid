package com.example.assessment.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.assessment.model.ui.PersonUI

@Entity
data class Person(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,
    val name: String
) : PersonUI, BaseModelItem {
    override fun getTitle(): String {
        return name
    }

    override fun getMovieId(): Long {
        return id ?: -1
    }

    override fun getType(): String = this.javaClass.simpleName

}
