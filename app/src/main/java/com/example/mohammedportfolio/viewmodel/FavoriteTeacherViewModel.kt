package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.FavoriteTeacherEntity
import com.example.mohammedportfolio.data.repository.FavoriteTeacherRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteTeacherViewModel(private val repository: FavoriteTeacherRepository) : ViewModel() {

    val allTeachers: StateFlow<List<FavoriteTeacherEntity>> = repository.allTeachers
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(teacher: FavoriteTeacherEntity) = viewModelScope.launch { repository.add(teacher) }
    fun update(teacher: FavoriteTeacherEntity) = viewModelScope.launch { repository.update(teacher) }
    fun delete(teacher: FavoriteTeacherEntity) = viewModelScope.launch { repository.delete(teacher) }
}