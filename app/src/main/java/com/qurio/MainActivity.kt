package com.qurio

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.qurio.ui.screen.onbording.OnboardingFragment
import androidx.core.content.edit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = getSharedPreferences("qurio_prefs", MODE_PRIVATE)
        val isFirstLaunch = prefs.getBoolean("first_launch", true)
        Log.d("prefs", "isFirstLaunch: $isFirstLaunch")

        if (isFirstLaunch) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.onboardingFragment, OnboardingFragment())
                .commit()

            prefs.edit { putBoolean("first_launch", false) }
        } else {
           // Load the main content fragment or activity
        }
    }
}