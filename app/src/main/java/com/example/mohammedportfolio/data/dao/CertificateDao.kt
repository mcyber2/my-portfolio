package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.CertificateEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CertificateDao {
    @Insert
    suspend fun insert(certificate: CertificateEntity): Long

    @Update
    suspend fun update(certificate: CertificateEntity)

    @Delete
    suspend fun delete(certificate: CertificateEntity)

    @Query("SELECT * FROM certificates ORDER BY date DESC")
    fun getAllCertificates(): Flow<List<CertificateEntity>>
}