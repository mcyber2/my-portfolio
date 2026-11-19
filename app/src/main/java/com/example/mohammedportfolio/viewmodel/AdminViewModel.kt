package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.repository.AdminRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AdminViewModel(private val repository: AdminRepository) : ViewModel() {

    // Login result: true = success, false = failure, null = no attempt yet
    private val _loginResult = MutableStateFlow<Boolean?>(null)
    val loginResult: StateFlow<Boolean?> = _loginResult

    // Change password result: true = success, false = wrong current password, null = idle
    private val _changePasswordResult = MutableStateFlow<Boolean?>(null)
    val changePasswordResult: StateFlow<Boolean?> = _changePasswordResult

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = repository.login(username, password)
        }
    }

    fun changePassword(username: String, currentPassword: String, newPassword: String) {
        viewModelScope.launch {
            _changePasswordResult.value = repository.changePassword(
                username = username,
                currentPassword = currentPassword,
                newPassword = newPassword
            )
        }
    }

    fun resetLoginResult() {
        _loginResult.value = null
    }

    fun resetChangePasswordResult() {
        _changePasswordResult.value = null
    }
}