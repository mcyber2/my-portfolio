package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.ProjectEntity
import com.example.mohammedportfolio.data.repository.ProjectRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProjectViewModel(private val repository: ProjectRepository) : ViewModel() {

    val allProjects: StateFlow<List<ProjectEntity>> = repository.allProjects
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(project: ProjectEntity) = viewModelScope.launch { repository.add(project) }
    fun update(project: ProjectEntity) = viewModelScope.launch { repository.update(project) }
    fun delete(project: ProjectEntity) = viewModelScope.launch { repository.delete(project) }
}