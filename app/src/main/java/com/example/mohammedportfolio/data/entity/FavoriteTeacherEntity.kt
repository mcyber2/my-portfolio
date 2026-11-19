package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "favorite_teachers")
data class FavoriteTeacherEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val subject: String,
    val reason: String
)