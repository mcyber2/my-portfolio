package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.SkillEntity
import com.example.mohammedportfolio.data.repository.SkillRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SkillViewModel(private val repository: SkillRepository) : ViewModel() {

    val allSkills: StateFlow<List<SkillEntity>> = repository.allSkills
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(skill: SkillEntity) = viewModelScope.launch { repository.add(skill) }
    fun update(skill: SkillEntity) = viewModelScope.launch { repository.update(skill) }
    fun delete(skill: SkillEntity) = viewModelScope.launch { repository.delete(skill) }
}