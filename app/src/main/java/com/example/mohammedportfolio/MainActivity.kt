package com.example.mohammedportfolio

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mohammedportfolio.databinding.ActivityMainBinding
import com.example.mohammedportfolio.ui.admin.login.AdminLoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigation.setupWithNavController(navController)

        // Long-press on toolbar title to open Admin Login
        binding.toolbar.setOnLongClickListener {
            startActivity(Intent(this, AdminLoginActivity::class.java))
            true
        }
    }
}