package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.InterestEntity
import com.example.mohammedportfolio.data.repository.InterestRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class InterestViewModel(private val repository: InterestRepository) : ViewModel() {

    val allInterests: StateFlow<List<InterestEntity>> = repository.allInterests
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(interest: InterestEntity) = viewModelScope.launch { repository.add(interest) }
    fun update(interest: InterestEntity) = viewModelScope.launch { repository.update(interest) }
    fun delete(interest: InterestEntity) = viewModelScope.launch { repository.delete(interest) }
}