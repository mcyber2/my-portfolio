package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.EducationDao
import com.example.mohammedportfolio.data.entity.EducationEntity
import kotlinx.coroutines.flow.Flow

class EducationRepository(private val dao: EducationDao) {
    val allEducation: Flow<List<EducationEntity>> = dao.getAllEducation()

    suspend fun add(education: EducationEntity): Long = dao.insert(education)
    suspend fun update(education: EducationEntity) = dao.update(education)
    suspend fun delete(education: EducationEntity) = dao.delete(education)
    suspend fun getById(id: Long): EducationEntity? = dao.getEducationById(id)
}