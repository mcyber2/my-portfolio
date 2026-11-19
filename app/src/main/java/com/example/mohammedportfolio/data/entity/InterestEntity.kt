package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "interests")
data class InterestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
)