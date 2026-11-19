package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "certificates")
data class CertificateEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val issuer: String,
    val date: String,
    val imageUri: String = ""
)