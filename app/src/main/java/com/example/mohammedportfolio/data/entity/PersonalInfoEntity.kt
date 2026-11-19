package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "personal_info")
data class PersonalInfoEntity(
    @PrimaryKey
    val id: Int = 1,
    val fullName: String = "",
    val dateOfBirth: String = "",
    val birthplace: String = "",
    val gender: String = "",
    val nationality: String = "",
    val phone: String = "",
    val email: String = "",
    val address: String = "",
    val biography: String = "",
    val careerObjective: String = "",
    val photoUri: String = "",
    val githubUrl: String = "",
    val linkedinUrl: String = ""
)