package com.example.assessment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.model.ui.StarWarsEntityUI
import com.example.assessment.repository.PersonRepository
import com.example.assessment.repository.StarshipRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personRepository: PersonRepository,
    private val starshipRepository: StarshipRepository
) : ViewModel() {

    /**
     * Combined flows of [starshipsStateFlow] and [peopleStateFlow]
     */
    private val selectedStarWarsEntity = MutableStateFlow<StarWarsEntityUI?>(null)

    private val starshipsStateFlow = MutableStateFlow<List<StarWarsEntityUI>>(emptyList())
    private val peopleStateFlow = MutableStateFlow<List<StarWarsEntityUI>>(emptyList())

    private val data: MutableStateFlow<List<StarWarsEntityUI>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            starshipsStateFlow.combine(peopleStateFlow) { starships, people ->
                starships.plus(people)
            }.collect { resultCombined ->
                data.value = resultCombined
            }
        }
    }

    fun initialDataPull() {
        viewModelScope.launch(Dispatchers.IO) {

            personRepository.getAll().collect {
                peopleStateFlow.value = it
            }

            starshipRepository.getAll().collect {
                starshipsStateFlow.value = it
            }
        }
    }

    fun getData() = data
    fun getSelectedEntity() = selectedStarWarsEntity

    fun setSelectedEntity(entity: StarWarsEntityUI) {
        viewModelScope.launch {
            selectedStarWarsEntity.value = entity
        }
    }
}