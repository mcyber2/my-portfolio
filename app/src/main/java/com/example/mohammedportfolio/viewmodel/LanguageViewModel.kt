package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.LanguageEntity
import com.example.mohammedportfolio.data.repository.LanguageRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LanguageViewModel(private val repository: LanguageRepository) : ViewModel() {

    val allLanguages: StateFlow<List<LanguageEntity>> = repository.allLanguages
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(language: LanguageEntity) = viewModelScope.launch { repository.add(language) }
    fun update(language: LanguageEntity) = viewModelScope.launch { repository.update(language) }
    fun delete(language: LanguageEntity) = viewModelScope.launch { repository.delete(language) }
}