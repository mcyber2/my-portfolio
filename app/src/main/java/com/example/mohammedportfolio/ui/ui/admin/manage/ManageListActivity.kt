package com.example.mohammedportfolio.ui.admin.manage

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.data.entity.*
import com.example.mohammedportfolio.viewmodel.*
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class ManageListActivity : AppCompatActivity() {

    private lateinit var adapter: ManageAdapter
    private var sectionKey: String = ""
    private lateinit var tvEmpty: TextView

    // ViewModels
    private val educationVM: EducationViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val skillVM: SkillViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val projectVM: ProjectViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val experienceVM: WorkExperienceViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val certificateVM: CertificateViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val interestVM: InterestViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val teacherVM: FavoriteTeacherViewModel by viewModels { ViewModelFactory(applicationContext) }
    private val languageVM: LanguageViewModel by viewModels { ViewModelFactory(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_list)

        sectionKey = intent.getStringExtra("section_key") ?: ""
        val title = intent.getStringExtra("section_title") ?: "Manage"

        findViewById<MaterialToolbar>(R.id.toolbar).title = title
        findViewById<MaterialToolbar>(R.id.toolbar).setNavigationOnClickListener { finish() }

        tvEmpty = findViewById(R.id.tvEmpty)
        val rv = findViewById<RecyclerView>(R.id.rvItems)
        val fab = findViewById<FloatingActionButton>(R.id.fabAdd)

        adapter = ManageAdapter(
            onEdit = { showEditDialog(it) },
            onDelete = { showDeleteDialog(it) }
        )
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        fab.setOnClickListener { showAddDialog() }

        observeData()
    }

    private fun observeData() {
        when (sectionKey) {
            "education" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    educationVM.allEducation.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.qualification, "${it.institution} · ${it.startYear}–${it.endYear}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "skills" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    skillVM.allSkills.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.name, "${it.category} · ${it.level}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "projects" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    projectVM.allProjects.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.title, "${it.technologies} · ${it.status}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "experience" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    experienceVM.allExperience.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.position, "${it.organization} · ${it.startDate}–${it.endDate}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "certificates" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    certificateVM.allCertificates.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.title, "${it.issuer} · ${it.date}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "interests" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    interestVM.allInterests.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.name, "", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "teachers" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    teacherVM.allTeachers.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.name, "${it.subject}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
            "languages" -> lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.STARTED) {
                    languageVM.allLanguages.collect { list ->
                        adapter.updateList(list.map {
                            ManageItem(it.id, it.name, "${it.proficiency}", it)
                        })
                        toggleEmpty(list.isEmpty())
                    }
                }
            }
        }
    }

    private fun toggleEmpty(isEmpty: Boolean) {
        tvEmpty.visibility = if (isEmpty) View.VISIBLE else View.GONE
    }

    private fun showAddDialog() {
        when (sectionKey) {
            "education" -> showEducationDialog(null)
            "skills" -> showSkillDialog(null)
            "projects" -> showProjectDialog(null)
            "experience" -> showExperienceDialog(null)
            "certificates" -> showCertificateDialog(null)
            "interests" -> showInterestDialog(null)
            "teachers" -> showTeacherDialog(null)
            "languages" -> showLanguageDialog(null)
        }
    }

    private fun showEditDialog(item: ManageItem) {
        when (sectionKey) {
            "education" -> showEducationDialog(item.rawData as EducationEntity)
            "skills" -> showSkillDialog(item.rawData as SkillEntity)
            "projects" -> showProjectDialog(item.rawData as ProjectEntity)
            "experience" -> showExperienceDialog(item.rawData as WorkExperienceEntity)
            "certificates" -> showCertificateDialog(item.rawData as CertificateEntity)
            "interests" -> showInterestDialog(item.rawData as InterestEntity)
            "teachers" -> showTeacherDialog(item.rawData as FavoriteTeacherEntity)
            "languages" -> showLanguageDialog(item.rawData as LanguageEntity)
        }
    }

    private fun showDeleteDialog(item: ManageItem) {
        AlertDialog.Builder(this)
            .setTitle("Delete")
            .setMessage("Delete \"${item.title}\"?")
            .setPositiveButton("Delete") { _, _ ->
                lifecycleScope.launch {
                    when (sectionKey) {
                        "education" -> educationVM.delete(item.rawData as EducationEntity)
                        "skills" -> skillVM.delete(item.rawData as SkillEntity)
                        "projects" -> projectVM.delete(item.rawData as ProjectEntity)
                        "experience" -> experienceVM.delete(item.rawData as WorkExperienceEntity)
                        "certificates" -> certificateVM.delete(item.rawData as CertificateEntity)
                        "interests" -> interestVM.delete(item.rawData as InterestEntity)
                        "teachers" -> teacherVM.delete(item.rawData as FavoriteTeacherEntity)
                        "languages" -> languageVM.delete(item.rawData as LanguageEntity)
                    }
                    Toast.makeText(this@ManageListActivity, "Deleted", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    // ---- Dialogs per section ----

    private fun showEducationDialog(existing: EducationEntity?) {
        val fields = listOf("Institution", "Qualification", "Field of Study", "Start Year", "End Year")
        showMultiFieldDialog(fields, existing?.let { listOf(it.institution, it.qualification, it.fieldOfStudy, it.startYear, it.endYear) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    educationVM.add(EducationEntity(institution = values[0], qualification = values[1], fieldOfStudy = values[2], startYear = values[3], endYear = values[4]))
                } else {
                    educationVM.update(existing.copy(institution = values[0], qualification = values[1], fieldOfStudy = values[2], startYear = values[3], endYear = values[4]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showSkillDialog(existing: SkillEntity?) {
        val fields = listOf("Name", "Category", "Level")
        showMultiFieldDialog(fields, existing?.let { listOf(it.name, it.category, it.level) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    skillVM.add(SkillEntity(name = values[0], category = values[1], level = values[2]))
                } else {
                    skillVM.update(existing.copy(name = values[0], category = values[1], level = values[2]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showProjectDialog(existing: ProjectEntity?) {
        val fields = listOf("Title", "Description", "Technologies", "Status", "Date")
        showMultiFieldDialog(fields, existing?.let { listOf(it.title, it.description, it.technologies, it.status, it.date) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    projectVM.add(ProjectEntity(title = values[0], description = values[1], technologies = values[2], status = values[3], date = values[4]))
                } else {
                    projectVM.update(existing.copy(title = values[0], description = values[1], technologies = values[2], status = values[3], date = values[4]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showExperienceDialog(existing: WorkExperienceEntity?) {
        val fields = listOf("Organization", "Position", "Responsibilities", "Start Date", "End Date")
        showMultiFieldDialog(fields, existing?.let { listOf(it.organization, it.position, it.responsibilities, it.startDate, it.endDate) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    experienceVM.add(WorkExperienceEntity(organization = values[0], position = values[1], responsibilities = values[2], startDate = values[3], endDate = values[4]))
                } else {
                    experienceVM.update(existing.copy(organization = values[0], position = values[1], responsibilities = values[2], startDate = values[3], endDate = values[4]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showCertificateDialog(existing: CertificateEntity?) {
        val fields = listOf("Title", "Issuer", "Date")
        showMultiFieldDialog(fields, existing?.let { listOf(it.title, it.issuer, it.date) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    certificateVM.add(CertificateEntity(title = values[0], issuer = values[1], date = values[2]))
                } else {
                    certificateVM.update(existing.copy(title = values[0], issuer = values[1], date = values[2]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showInterestDialog(existing: InterestEntity?) {
        val fields = listOf("Name")
        showMultiFieldDialog(fields, existing?.let { listOf(it.name) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    interestVM.add(InterestEntity(name = values[0]))
                } else {
                    interestVM.update(existing.copy(name = values[0]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showTeacherDialog(existing: FavoriteTeacherEntity?) {
        val fields = listOf("Name", "Subject", "Reason")
        showMultiFieldDialog(fields, existing?.let { listOf(it.name, it.subject, it.reason) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    teacherVM.add(FavoriteTeacherEntity(name = values[0], subject = values[1], reason = values[2]))
                } else {
                    teacherVM.update(existing.copy(name = values[0], subject = values[1], reason = values[2]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showLanguageDialog(existing: LanguageEntity?) {
        val fields = listOf("Name", "Proficiency")
        showMultiFieldDialog(fields, existing?.let { listOf(it.name, it.proficiency) }) { values ->
            lifecycleScope.launch {
                if (existing == null) {
                    languageVM.add(LanguageEntity(name = values[0], proficiency = values[1]))
                } else {
                    languageVM.update(existing.copy(name = values[0], proficiency = values[1]))
                }
                Toast.makeText(this@ManageListActivity, "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * Generic multi-field dialog.
     */
    private fun showMultiFieldDialog(
        labels: List<String>,
        initialValues: List<String>?,
        onSave: (List<String>) -> Unit
    ) {
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 20, 40, 0)
        }

        val editTexts = mutableListOf<EditText>()
        labels.forEachIndexed { index, label ->
            val et = EditText(this).apply {
                hint = label
                inputType = InputType.TYPE_CLASS_TEXT
                setText(initialValues?.getOrNull(index) ?: "")
            }
            editTexts.add(et)
            container.addView(et)
        }

        AlertDialog.Builder(this)
            .setTitle(if (initialValues == null) "Add" else "Edit")
            .setView(container)
            .setPositiveButton("Save") { _, _ ->
                val values = editTexts.map { it.text.toString().trim() }
                if (values.any { it.isBlank() && labels[values.indexOf(it)] != "Reason" }) {
                    // allow empty only for some fields; simple validation
                }
                onSave(values)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}