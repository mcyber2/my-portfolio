package com.example.mohammedportfolio.ui.admin.manage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.data.entity.PersonalInfoEntity
import com.example.mohammedportfolio.viewmodel.PersonalInfoViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class EditPersonalInfoActivity : AppCompatActivity() {

    private val viewModel: PersonalInfoViewModel by viewModels {
        ViewModelFactory(applicationContext)
    }

    private lateinit var etFullName: TextInputEditText
    private lateinit var etDob: TextInputEditText
    private lateinit var etBirthplace: TextInputEditText
    private lateinit var etGender: TextInputEditText
    private lateinit var etNationality: TextInputEditText
    private lateinit var etPhone: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etAddress: TextInputEditText
    private lateinit var etBio: TextInputEditText
    private lateinit var etCareerObjective: TextInputEditText
    private lateinit var etGithub: TextInputEditText
    private lateinit var etLinkedin: TextInputEditText
    private lateinit var btnSave: MaterialButton

    private var currentInfo: PersonalInfoEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_personal_info)

        etFullName = findViewById(R.id.etFullName)
        etDob = findViewById(R.id.etDob)
        etBirthplace = findViewById(R.id.etBirthplace)
        etGender = findViewById(R.id.etGender)
        etNationality = findViewById(R.id.etNationality)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etBio = findViewById(R.id.etBio)
        etCareerObjective = findViewById(R.id.etCareerObjective)
        etGithub = findViewById(R.id.etGithub)
        etLinkedin = findViewById(R.id.etLinkedin)
        btnSave = findViewById(R.id.btnSaveInfo)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.personalInfo.collect { info ->
                    if (info != null) {
                        currentInfo = info
                        populateFields(info)
                    }
                }
            }
        }

        btnSave.setOnClickListener { saveChanges() }
    }

    private fun populateFields(info: PersonalInfoEntity) {
        etFullName.setText(info.fullName)
        etDob.setText(info.dateOfBirth)
        etBirthplace.setText(info.birthplace)
        etGender.setText(info.gender)
        etNationality.setText(info.nationality)
        etPhone.setText(info.phone)
        etEmail.setText(info.email)
        etAddress.setText(info.address)
        etBio.setText(info.biography)
        etCareerObjective.setText(info.careerObjective)
        etGithub.setText(info.githubUrl)
        etLinkedin.setText(info.linkedinUrl)
    }

    private fun saveChanges() {
        val base = currentInfo ?: PersonalInfoEntity(id = 1)

        val updated = base.copy(
            fullName = etFullName.text.toString().trim(),
            dateOfBirth = etDob.text.toString().trim(),
            birthplace = etBirthplace.text.toString().trim(),
            gender = etGender.text.toString().trim(),
            nationality = etNationality.text.toString().trim(),
            phone = etPhone.text.toString().trim(),
            email = etEmail.text.toString().trim(),
            address = etAddress.text.toString().trim(),
            biography = etBio.text.toString().trim(),
            careerObjective = etCareerObjective.text.toString().trim(),
            githubUrl = etGithub.text.toString().trim(),
            linkedinUrl = etLinkedin.text.toString().trim()
        )

        viewModel.save(updated)
        Toast.makeText(this, "Personal info saved ✅", Toast.LENGTH_SHORT).show()
        finish()
    }
}