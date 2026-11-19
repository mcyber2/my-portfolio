package com.example.mohammedportfolio.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mohammedportfolio.data.database.AppDatabase
import com.example.mohammedportfolio.data.repository.*

class ViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val db = AppDatabase.getDatabase(context)

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(PersonalInfoViewModel::class.java) ->
                PersonalInfoViewModel(PersonalInfoRepository(db.personalInfoDao())) as T

            modelClass.isAssignableFrom(EducationViewModel::class.java) ->
                EducationViewModel(EducationRepository(db.educationDao())) as T

            modelClass.isAssignableFrom(SkillViewModel::class.java) ->
                SkillViewModel(SkillRepository(db.skillDao())) as T

            modelClass.isAssignableFrom(ProjectViewModel::class.java) ->
                ProjectViewModel(ProjectRepository(db.projectDao())) as T

            modelClass.isAssignableFrom(WorkExperienceViewModel::class.java) ->
                WorkExperienceViewModel(WorkExperienceRepository(db.workExperienceDao())) as T

            modelClass.isAssignableFrom(CertificateViewModel::class.java) ->
                CertificateViewModel(CertificateRepository(db.certificateDao())) as T

            modelClass.isAssignableFrom(InterestViewModel::class.java) ->
                InterestViewModel(InterestRepository(db.interestDao())) as T

            modelClass.isAssignableFrom(FavoriteTeacherViewModel::class.java) ->
                FavoriteTeacherViewModel(FavoriteTeacherRepository(db.favoriteTeacherDao())) as T

            modelClass.isAssignableFrom(LanguageViewModel::class.java) ->
                LanguageViewModel(LanguageRepository(db.languageDao())) as T

            modelClass.isAssignableFrom(AdminViewModel::class.java) ->
                AdminViewModel(AdminRepository(db.adminUserDao())) as T

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}