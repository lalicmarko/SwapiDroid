package com.example.assessment.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assessment.model.ui.PersonUI
import com.example.assessment.repository.PersonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val personRepository: PersonRepository
) : ViewModel() {
    private val people = MutableLiveData<List<PersonUI>>(emptyList())

    fun initialDataPull() {
        viewModelScope.launch(Dispatchers.IO) {

            personRepository.getAll().collect {
                people.postValue(it)
            }
        }
    }

    fun observeMovies() = people
}