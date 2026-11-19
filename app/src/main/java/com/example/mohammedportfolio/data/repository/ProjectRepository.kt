package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.ProjectDao
import com.example.mohammedportfolio.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

class ProjectRepository(private val dao: ProjectDao) {
    val allProjects: Flow<List<ProjectEntity>> = dao.getAllProjects()

    suspend fun add(project: ProjectEntity): Long = dao.insert(project)
    suspend fun update(project: ProjectEntity) = dao.update(project)
    suspend fun delete(project: ProjectEntity) = dao.delete(project)
    suspend fun getById(id: Long): ProjectEntity? = dao.getProjectById(id)
}