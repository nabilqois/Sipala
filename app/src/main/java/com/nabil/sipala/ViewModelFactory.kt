package com.nabil.sipala

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThemeViewModel::class.java)) {
            return ThemeViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(HomeActivity::class.java)) {
            return ThemeViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(MainActivity::class.java)) {
            return ThemeViewModel(pref) as T
        }
        if (modelClass.isAssignableFrom(HasilActivity::class.java)) {
            return ThemeViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}