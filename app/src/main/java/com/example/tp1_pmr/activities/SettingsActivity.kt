package com.example.tp1_pmr.activities

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1_pmr.R

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings_activity)

        val pseudo: String? = intent.getStringExtra("pseudo")
        val text : TextView = findViewById(R.id.name)
        text.text = "Profile \""+pseudo+"\" logged in"
    }
}