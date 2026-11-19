package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.SkillDao
import com.example.mohammedportfolio.data.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

class SkillRepository(private val dao: SkillDao) {
    val allSkills: Flow<List<SkillEntity>> = dao.getAllSkills()

    suspend fun add(skill: SkillEntity): Long = dao.insert(skill)
    suspend fun update(skill: SkillEntity) = dao.update(skill)
    suspend fun delete(skill: SkillEntity) = dao.delete(skill)
    suspend fun getById(id: Long): SkillEntity? = dao.getSkillById(id)
}