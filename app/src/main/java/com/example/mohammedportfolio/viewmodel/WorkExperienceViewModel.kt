package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.WorkExperienceEntity
import com.example.mohammedportfolio.data.repository.WorkExperienceRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WorkExperienceViewModel(private val repository: WorkExperienceRepository) : ViewModel() {

    val allExperience: StateFlow<List<WorkExperienceEntity>> = repository.allExperience
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(exp: WorkExperienceEntity) = viewModelScope.launch { repository.add(exp) }
    fun update(exp: WorkExperienceEntity) = viewModelScope.launch { repository.update(exp) }
    fun delete(exp: WorkExperienceEntity) = viewModelScope.launch { repository.delete(exp) }
}