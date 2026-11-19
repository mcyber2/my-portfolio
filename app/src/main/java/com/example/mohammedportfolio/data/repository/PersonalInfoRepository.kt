package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.PersonalInfoDao
import com.example.mohammedportfolio.data.entity.PersonalInfoEntity
import kotlinx.coroutines.flow.Flow

class PersonalInfoRepository(private val dao: PersonalInfoDao) {
    val personalInfo: Flow<PersonalInfoEntity?> = dao.getPersonalInfo()

    suspend fun save(info: PersonalInfoEntity) {
        dao.insert(info)
    }

    suspend fun getOnce(): PersonalInfoEntity? = dao.getPersonalInfoOnce()
}