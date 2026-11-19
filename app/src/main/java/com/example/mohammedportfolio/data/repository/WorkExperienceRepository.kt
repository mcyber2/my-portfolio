package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.WorkExperienceDao
import com.example.mohammedportfolio.data.entity.WorkExperienceEntity
import kotlinx.coroutines.flow.Flow

class WorkExperienceRepository(private val dao: WorkExperienceDao) {
    val allExperience: Flow<List<WorkExperienceEntity>> = dao.getAllExperience()

    suspend fun add(exp: WorkExperienceEntity): Long = dao.insert(exp)
    suspend fun update(exp: WorkExperienceEntity) = dao.update(exp)
    suspend fun delete(exp: WorkExperienceEntity) = dao.delete(exp)
}