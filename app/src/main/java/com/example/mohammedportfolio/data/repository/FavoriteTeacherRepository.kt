package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.FavoriteTeacherDao
import com.example.mohammedportfolio.data.entity.FavoriteTeacherEntity
import kotlinx.coroutines.flow.Flow

class FavoriteTeacherRepository(private val dao: FavoriteTeacherDao) {
    val allTeachers: Flow<List<FavoriteTeacherEntity>> = dao.getAllTeachers()

    suspend fun add(teacher: FavoriteTeacherEntity): Long = dao.insert(teacher)
    suspend fun update(teacher: FavoriteTeacherEntity) = dao.update(teacher)
    suspend fun delete(teacher: FavoriteTeacherEntity) = dao.delete(teacher)
}