package com.example.mohammedportfolio.data.repository

import com.example.mohammedportfolio.data.dao.LanguageDao
import com.example.mohammedportfolio.data.entity.LanguageEntity
import kotlinx.coroutines.flow.Flow

class LanguageRepository(private val dao: LanguageDao) {
    val allLanguages: Flow<List<LanguageEntity>> = dao.getAllLanguages()

    suspend fun add(language: LanguageEntity): Long = dao.insert(language)
    suspend fun update(language: LanguageEntity) = dao.update(language)
    suspend fun delete(language: LanguageEntity) = dao.delete(language)
}