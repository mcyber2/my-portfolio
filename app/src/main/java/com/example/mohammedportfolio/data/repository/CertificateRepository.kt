package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.CertificateDao
import com.example.mohammedportfolio.data.entity.CertificateEntity
import kotlinx.coroutines.flow.Flow

class CertificateRepository(private val dao: CertificateDao) {
    val allCertificates: Flow<List<CertificateEntity>> = dao.getAllCertificates()

    suspend fun add(cert: CertificateEntity): Long = dao.insert(cert)
    suspend fun update(cert: CertificateEntity) = dao.update(cert)
    suspend fun delete(cert: CertificateEntity) = dao.delete(cert)
}