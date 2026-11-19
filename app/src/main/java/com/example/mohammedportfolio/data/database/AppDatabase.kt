package com.example.mohammedportfolio.data.database

import android.content.Context
import androidx.room3.Database
import androidx.room3.Room
import androidx.room3.RoomDatabase
import com.example.mohammedportfolio.data.dao.*
import com.example.mohammedportfolio.data.entity.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [
        PersonalInfoEntity::class,
        EducationEntity::class,
        SkillEntity::class,
        ProjectEntity::class,
        WorkExperienceEntity::class,
        CertificateEntity::class,
        InterestEntity::class,
        FavoriteTeacherEntity::class,
        LanguageEntity::class,
        AdminUserEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun personalInfoDao(): PersonalInfoDao
    abstract fun educationDao(): EducationDao
    abstract fun skillDao(): SkillDao
    abstract fun projectDao(): ProjectDao
    abstract fun workExperienceDao(): WorkExperienceDao
    abstract fun certificateDao(): CertificateDao
    abstract fun interestDao(): InterestDao
    abstract fun favoriteTeacherDao(): FavoriteTeacherDao
    abstract fun languageDao(): LanguageDao
    abstract fun adminUserDao(): AdminUserDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mohammed_portfolio_db"
                ).build()
                INSTANCE = instance

                // Seed data only when the database is empty (check if admin table has records)
                CoroutineScope(Dispatchers.IO).launch {
                    if (instance.adminUserDao().count() == 0) {
                        seedDatabase(instance)
                    }
                }

                instance
            }
        }

        private suspend fun seedDatabase(database: AppDatabase) {
            // Personal info
            database.personalInfoDao().insert(
                PersonalInfoEntity(
                    id = 1,
                    fullName = "Mohammed Juneydi Ahmed",
                    dateOfBirth = "Placeholder",
                    birthplace = "Placeholder",
                    gender = "Placeholder",
                    nationality = "Placeholder",
                    phone = "Placeholder",
                    email = "Placeholder",
                    address = "Placeholder",
                    biography = "I am a passionate Computer Science student with a strong interest in mobile application development. I enjoy building clean, user-friendly Android applications using Kotlin and modern architecture patterns.",
                    careerObjective = "To become a professional Android Developer building impactful mobile applications.",
                    photoUri = ""
                )
            )

            // Education
            database.educationDao().insert(
                EducationEntity(
                    institution = "Addis Ababa University",
                    qualification = "BSc in Computer Science",
                    fieldOfStudy = "Computer Science",
                    startYear = "2022",
                    endYear = "2026 (Expected)"
                )
            )

            // Skills
            val skills = listOf(
                SkillEntity(name = "Kotlin", category = "Programming", level = "Intermediate"),
                SkillEntity(name = "Java", category = "Programming", level = "Intermediate"),
                SkillEntity(name = "XML", category = "Programming", level = "Intermediate"),
                SkillEntity(name = "SQL", category = "Programming", level = "Beginner"),
                SkillEntity(name = "Git", category = "Tools", level = "Intermediate"),
                SkillEntity(name = "Android Studio", category = "Tools", level = "Intermediate"),
                SkillEntity(name = "Room Database", category = "Tools", level = "Beginner"),
                SkillEntity(name = "Problem Solving", category = "Soft Skills", level = "Advanced"),
                SkillEntity(name = "Teamwork", category = "Soft Skills", level = "Advanced"),
                SkillEntity(name = "Communication", category = "Soft Skills", level = "Intermediate")
            )
            skills.forEach { database.skillDao().insert(it) }

            // Projects
            database.projectDao().insert(
                ProjectEntity(
                    title = "Personal CV & Portfolio App",
                    description = "A comprehensive Android app showcasing my CV, projects, and skills with an admin dashboard for CRUD operations.",
                    technologies = "Kotlin, XML, Room, MVVM",
                    githubLink = "",
                    status = "In Progress",
                    date = "2026"
                )
            )
            database.projectDao().insert(
                ProjectEntity(
                    title = "Todo List App",
                    description = "A simple task management app with RecyclerView and local storage.",
                    technologies = "Kotlin, RecyclerView, SQLite",
                    githubLink = "",
                    status = "Completed",
                    date = "2025"
                )
            )
            database.projectDao().insert(
                ProjectEntity(
                    title = "Student Record System",
                    description = "A console-based student record management system built as a course project.",
                    technologies = "Java, SQLite",
                    githubLink = "",
                    status = "Completed",
                    date = "2024"
                )
            )

            // Interests
            listOf("Coding", "Reading", "Football", "Music", "Learning New Technologies")
                .forEach { database.interestDao().insert(InterestEntity(name = it)) }

            // Favorite Teachers
            database.favoriteTeacherDao().insert(
                FavoriteTeacherEntity(
                    name = "Mr. Ahmed Yusuf",
                    subject = "Programming Fundamentals",
                    reason = "Clear explanations and great patience with students."
                )
            )
            database.favoriteTeacherDao().insert(
                FavoriteTeacherEntity(
                    name = "Ms. Sara Bekele",
                    subject = "Database Systems",
                    reason = "Inspiring teaching style and very organized."
                )
            )

            // Certificates
            database.certificateDao().insert(
                CertificateEntity(
                    title = "Android Basics Certificate",
                    issuer = "Google / Udemy",
                    date = "2025"
                )
            )
            database.certificateDao().insert(
                CertificateEntity(
                    title = "Computer Science Department Award",
                    issuer = "University",
                    date = "2025"
                )
            )

            // Languages
            database.languageDao().insert(LanguageEntity(name = "Amharic", proficiency = "Native"))
            database.languageDao().insert(LanguageEntity(name = "English", proficiency = "Fluent"))
            database.languageDao().insert(LanguageEntity(name = "Arabic", proficiency = "Basic"))

            // Admin user (default: admin / admin123)
// Password is stored as SHA-256 hash (not plain text)
            database.adminUserDao().insert(
                AdminUserEntity(
                    username = "eng/mame",
                    passwordHash = com.example.mohammedportfolio.utils.HashUtils.sha256("Syntax1.pyt")
                )
            )

        }
    }
}