package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener {
            finish()
        }

        binding.cardLogout.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
//            Firebase.auth.signOut()
            val toMain = Intent(this, MainActivity::class.java)
            toMain.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(toMain)
        }

        binding.cardTheme.setOnClickListener {
            val toTheme = Intent(this, ThemeActivity::class.java)
            startActivity(toTheme)
        }
    }
}