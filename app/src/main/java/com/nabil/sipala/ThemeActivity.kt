package com.nabil.sipala

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.switchmaterial.SwitchMaterial
import com.nabil.sipala.databinding.ActivityThemeBinding

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ThemeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThemeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThemeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = SettingPreferences.getInstance(dataStore)
        val mainViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            ThemeViewModel::class.java
        )
        mainViewModel.getThemeSettings().observe(
            this
        ) { isDarkModeActive: Boolean ->
            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switchTheme.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switchTheme.isChecked = false
            }
        }

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            mainViewModel.saveThemeSetting(isChecked)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }
}