package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.ProjectEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert
    suspend fun insert(project: ProjectEntity): Long

    @Update
    suspend fun update(project: ProjectEntity)

    @Delete
    suspend fun delete(project: ProjectEntity)

    @Query("SELECT * FROM projects ORDER BY date DESC")
    fun getAllProjects(): Flow<List<ProjectEntity>>

    @Query("SELECT * FROM projects WHERE id = :id")
    suspend fun getProjectById(id: Long): ProjectEntity?
}