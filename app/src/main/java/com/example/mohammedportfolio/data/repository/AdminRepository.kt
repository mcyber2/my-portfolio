package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.AdminUserDao
import com.example.mohammedportfolio.data.entity.AdminUserEntity
import com.example.mohammedportfolio.utils.HashUtils

class AdminRepository(private val dao: AdminUserDao) {

    suspend fun login(username: String, password: String): Boolean {
        val admin = dao.getAdminByUsername(username) ?: return false
        val enteredHash = HashUtils.sha256(password)
        return admin.passwordHash == enteredHash
    }

    suspend fun register(username: String, password: String) {
        dao.insert(
            AdminUserEntity(
                username = username,
                passwordHash = HashUtils.sha256(password)
            )
        )
    }

    /**
     * Change the password of an existing admin.
     * Returns true if the current password matched and the change was saved.
     */
    suspend fun changePassword(
        username: String,
        currentPassword: String,
        newPassword: String
    ): Boolean {
        val admin = dao.getAdminByUsername(username) ?: return false
        val currentHash = HashUtils.sha256(currentPassword)
        if (admin.passwordHash != currentHash) return false

        val updated = admin.copy(passwordHash = HashUtils.sha256(newPassword))
        dao.update(updated)
        return true
    }
}