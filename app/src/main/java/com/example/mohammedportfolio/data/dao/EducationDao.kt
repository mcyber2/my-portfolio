package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.EducationEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EducationDao {
    @Insert
    suspend fun insert(education: EducationEntity): Long

    @Update
    suspend fun update(education: EducationEntity)

    @Delete
    suspend fun delete(education: EducationEntity)

    @Query("SELECT * FROM education ORDER BY startYear DESC")
    fun getAllEducation(): Flow<List<EducationEntity>>

    @Query("SELECT * FROM education WHERE id = :id")
    suspend fun getEducationById(id: Long): EducationEntity?
}