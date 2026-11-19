package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "projects")
data class ProjectEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val technologies: String,
    val githubLink: String = "",
    val status: String = "Completed",
    val date: String = "",
    val imageUri: String = ""
)