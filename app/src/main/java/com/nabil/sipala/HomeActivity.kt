package com.nabil.sipala

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nabil.sipala.databinding.ActivityHomeBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )

        mainViewModel.getThemeSettings().observe(
            this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                binding.tvProfiles.setTextColor(ContextCompat.getColor(this, R.color.black))
                binding.tvSettings.setTextColor(ContextCompat.getColor(this, R.color.black))
            } else {
                binding.tvProfiles.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.tvSettings.setTextColor(ContextCompat.getColor(this, R.color.white))

            }
        }

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