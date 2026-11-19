package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.SkillEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SkillDao {
    @Insert
    suspend fun insert(skill: SkillEntity): Long

    @Update
    suspend fun update(skill: SkillEntity)

    @Delete
    suspend fun delete(skill: SkillEntity)

    @Query("SELECT * FROM skills ORDER BY category, name")
    fun getAllSkills(): Flow<List<SkillEntity>>

    @Query("SELECT * FROM skills WHERE id = :id")
    suspend fun getSkillById(id: Long): SkillEntity?
}