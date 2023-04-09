package com.nabil.sipala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nabil.sipala.databinding.ActivityTipsKesehatanLambungBinding

class TipsKesehatanLambungActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTipsKesehatanLambungBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsKesehatanLambungBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.setNavigationOnClickListener { finish() }
    }
}