package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "skills")
data class SkillEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val category: String,
    val level: String
)