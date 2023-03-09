package com.nabil.sipala

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.nabil.sipala.databinding.ActivityHasilBinding
import java.util.ArrayList

class HasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilBinding
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val premis = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("EXTRA", Premis::class.java)
        } else {
            intent.getParcelableExtra<Premis>("EXTRA")
        }
        Log.d("Hasil", premis.toString())


    }

    companion object {
        const val EXTRA = "extra"
    }
}