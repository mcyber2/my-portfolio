package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.FavoriteTeacherEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteTeacherDao {
    @Insert
    suspend fun insert(teacher: FavoriteTeacherEntity): Long

    @Update
    suspend fun update(teacher: FavoriteTeacherEntity)

    @Delete
    suspend fun delete(teacher: FavoriteTeacherEntity)

    @Query("SELECT * FROM favorite_teachers ORDER BY name")
    fun getAllTeachers(): Flow<List<FavoriteTeacherEntity>>
}