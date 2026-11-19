package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.PersonalInfoEntity
import com.example.mohammedportfolio.data.repository.PersonalInfoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PersonalInfoViewModel(private val repository: PersonalInfoRepository) : ViewModel() {

    val personalInfo: StateFlow<PersonalInfoEntity?> = repository.personalInfo
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun save(info: PersonalInfoEntity) {
        viewModelScope.launch { repository.save(info) }
    }
}