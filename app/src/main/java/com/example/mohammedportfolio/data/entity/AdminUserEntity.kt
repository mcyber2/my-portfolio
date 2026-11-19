package com.example.mohammedportfolio.data.entity

import androidx.room3.Entity
import androidx.room3.PrimaryKey


@Entity(tableName = "admin_users")
data class AdminUserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val username: String,
    val passwordHash: String
)