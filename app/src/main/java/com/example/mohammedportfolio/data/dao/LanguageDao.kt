package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.Update
import androidx.room3.Delete
import androidx.room3.Query
import com.example.mohammedportfolio.data.entity.LanguageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {
    @Insert
    suspend fun insert(language: LanguageEntity): Long

    @Update
    suspend fun update(language: LanguageEntity)

    @Delete
    suspend fun delete(language: LanguageEntity)

    @Query("SELECT * FROM languages ORDER BY name")
    fun getAllLanguages(): Flow<List<LanguageEntity>>
}