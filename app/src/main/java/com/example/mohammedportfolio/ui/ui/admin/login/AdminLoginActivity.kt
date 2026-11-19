package com.example.mohammedportfolio.ui.admin.login

import android.content.Intent
import android.os.Bundle

import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mohammedportfolio.R
import com.example.mohammedportfolio.ui.admin.dashboard.AdminDashboardActivity
import com.example.mohammedportfolio.viewmodel.AdminViewModel
import com.example.mohammedportfolio.viewmodel.ViewModelFactory
import androidx.activity.viewModels
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class AdminLoginActivity : AppCompatActivity() {

    private val viewModel: AdminViewModel by viewModels {
        ViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val password = etPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                Toast.makeText(this, "Enter username and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.login(username, password)
        }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.loginResult.collect { result ->
                    when (result) {
                        true -> {
                            startActivity(Intent(this@AdminLoginActivity, AdminDashboardActivity::class.java))
                            finish()
                        }
                        false -> {
                            Toast.makeText(this@AdminLoginActivity, "Invalid credentials", Toast.LENGTH_SHORT).show()
                            viewModel.resetLoginResult()
                        }
                        null -> { /* idle */ }
                    }
                }
            }
        }
    }
}