package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.InterestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface InterestDao {
    @Insert
    suspend fun insert(interest: InterestEntity): Long

    @Update
    suspend fun update(interest: InterestEntity)

    @Delete
    suspend fun delete(interest: InterestEntity)

    @Query("SELECT * FROM interests ORDER BY name")
    fun getAllInterests(): Flow<List<InterestEntity>>
}