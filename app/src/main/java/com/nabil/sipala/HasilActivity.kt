package com.nabil.sipala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.nabil.sipala.databinding.ActivityHasilBinding

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
        if (premis != null) {
            for ((index, value) in premis.premis.withIndex()) {
                binding.tvGejalaContent.append("${index+1}. ${value.name} \n")
            }
        }
//        binding.tvGejalaContent.text = premis?.listGejala?.get(0)?.name ?: "text"
        binding.btnBackHome.setOnClickListener {
            val toHome = Intent(this, MainActivity::class.java)
            toHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(toHome)
        }
    }

    companion object {
        const val EXTRA = "extra"
    }
}