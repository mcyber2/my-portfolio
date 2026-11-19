package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey

@Entity(tableName = "work_experience")
data class WorkExperienceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val organization: String,
    val position: String,
    val responsibilities: String,
    val startDate: String,
    val endDate: String
)