package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Firebase.auth.currentUser
        if (user == null) {
            binding.tvUsername.text = "Guest"
            Log.d("user firebase", "kosong")
        }

        user?.let {
            if (it.displayName?.isEmpty() == true) {
                binding.tvUsername.text = it.email
                Log.d("firebase", "kosong")
            } else {
                binding.tvUsername.text = it.displayName
            }
        }
        user?.displayName?.let { Log.d("displayName", it) }

        binding.cardDiagnosaGejala.setOnClickListener {
            startActivity(Intent(this, DiagnosaActivity::class.java))
        }

        binding.cardSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        binding.imgBtnSettings.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        binding.cardProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        binding.imgBtnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()

        val user = Firebase.auth.currentUser

        runOnUiThread {
            if (user != null) {
                binding.tvUsername.text = user.displayName
            }
        }
    }
}