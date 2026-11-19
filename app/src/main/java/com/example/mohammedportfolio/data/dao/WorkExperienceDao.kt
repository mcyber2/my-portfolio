package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.WorkExperienceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkExperienceDao {
    @Insert
    suspend fun insert(experience: WorkExperienceEntity): Long

    @Update
    suspend fun update(experience: WorkExperienceEntity)

    @Delete
    suspend fun delete(experience: WorkExperienceEntity)

    @Query("SELECT * FROM work_experience ORDER BY startDate DESC")
    fun getAllExperience(): Flow<List<WorkExperienceEntity>>
}