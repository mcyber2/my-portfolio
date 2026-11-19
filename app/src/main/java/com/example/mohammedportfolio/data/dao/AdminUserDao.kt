package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Query
import androidx.room3.Update
import com.example.mohammedportfolio.data.entity.AdminUserEntity

@Dao
interface AdminUserDao {
    @Insert
    suspend fun insert(admin: AdminUserEntity): Long

    @Update
    suspend fun update(admin: AdminUserEntity)

    @Query("SELECT * FROM admin_users WHERE username = :username LIMIT 1")
    suspend fun getAdminByUsername(username: String): AdminUserEntity?

    @Query("SELECT COUNT(*) FROM admin_users")
    suspend fun count(): Int
}