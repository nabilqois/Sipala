package com.nabil.sipala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nabil.sipala.databinding.ActivityHasilBinding

class HasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val array = intent.getStringArrayListExtra(EXTRA)

        array?.forEach {
            binding.tvExtra.text = it
        }
    }

    companion object {
        const val EXTRA = "extra"
    }
}