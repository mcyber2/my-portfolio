package com.example.mohammedportfolio.ui.admin.dashboard

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.ui.admin.manage.EditPersonalInfoActivity
import com.example.mohammedportfolio.ui.admin.manage.ManageListActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        // Personal Info → opens dedicated editor
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardPersonalInfo)?.setOnClickListener {
            startActivity(Intent(this, EditPersonalInfoActivity::class.java))
        }

        // Other sections → open generic list
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardEducation)?.setOnClickListener {
            openManage("education", "Education")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardSkills)?.setOnClickListener {
            openManage("skills", "Skills")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardProjects)?.setOnClickListener {
            openManage("projects", "Projects")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardExperience)?.setOnClickListener {
            openManage("experience", "Work Experience")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardCertificates)?.setOnClickListener {
            openManage("certificates", "Certificates")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardInterests)?.setOnClickListener {
            openManage("interests", "Interests")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardTeachers)?.setOnClickListener {
            openManage("teachers", "Favorite Teachers")
        }
        findViewById<com.google.android.material.card.MaterialCardView>(R.id.cardLanguages)?.setOnClickListener {
            openManage("languages", "Languages")
        }

        findViewById<Button>(R.id.btnLogout)?.setOnClickListener {
            finish()
        }
    }

    private fun openManage(sectionKey: String, sectionTitle: String) {
        val intent = Intent(this, ManageListActivity::class.java).apply {
            putExtra("section_key", sectionKey)
            putExtra("section_title", sectionTitle)
        }
        startActivity(intent)
    }
}