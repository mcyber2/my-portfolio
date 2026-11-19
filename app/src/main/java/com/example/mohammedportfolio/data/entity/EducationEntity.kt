package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "education")
data class EducationEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val institution: String,
    val qualification: String,
    val fieldOfStudy: String,
    val startYear: String,
    val endYear: String
)