package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.EducationEntity
import com.example.mohammedportfolio.data.repository.EducationRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EducationViewModel(private val repository: EducationRepository) : ViewModel() {

    val allEducation: StateFlow<List<EducationEntity>> = repository.allEducation
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(education: EducationEntity) = viewModelScope.launch { repository.add(education) }
    fun update(education: EducationEntity) = viewModelScope.launch { repository.update(education) }
    fun delete(education: EducationEntity) = viewModelScope.launch { repository.delete(education) }
}