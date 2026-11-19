package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.InterestDao
import com.example.mohammedportfolio.data.entity.InterestEntity
import kotlinx.coroutines.flow.Flow

class InterestRepository(private val dao: InterestDao) {
    val allInterests: Flow<List<InterestEntity>> = dao.getAllInterests()

    suspend fun add(interest: InterestEntity): Long = dao.insert(interest)
    suspend fun update(interest: InterestEntity) = dao.update(interest)
    suspend fun delete(interest: InterestEntity) = dao.delete(interest)
}