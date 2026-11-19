package com.example.mohammedportfolio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mohammedportfolio.data.entity.CertificateEntity
import com.example.mohammedportfolio.data.repository.CertificateRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CertificateViewModel(private val repository: CertificateRepository) : ViewModel() {

    val allCertificates: StateFlow<List<CertificateEntity>> = repository.allCertificates
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun add(cert: CertificateEntity) = viewModelScope.launch { repository.add(cert) }
    fun update(cert: CertificateEntity) = viewModelScope.launch { repository.update(cert) }
    fun delete(cert: CertificateEntity) = viewModelScope.launch { repository.delete(cert) }
}