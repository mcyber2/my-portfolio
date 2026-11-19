package com.example.mohammedportfolio.data.dao

import androidx.room3.Dao
import androidx.room3.Insert
import androidx.room3.OnConflictStrategy
import androidx.room3.Query
import androidx.room3.Update
import com.example.mohammedportfolio.data.entity.PersonalInfoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonalInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(info: PersonalInfoEntity)

    @Update
    suspend fun update(info: PersonalInfoEntity)

    @Query("SELECT * FROM personal_info WHERE id = 1")
    fun getPersonalInfo(): Flow<PersonalInfoEntity?>

    @Query("SELECT * FROM personal_info WHERE id = 1")
    suspend fun getPersonalInfoOnce(): PersonalInfoEntity?
}