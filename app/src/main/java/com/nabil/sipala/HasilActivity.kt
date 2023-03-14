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
    private val rule = mapOf<String, List<String>>(
        "R1" to listOf("G1", "G2", "G3", "G4"),
        "R2" to listOf("G1", "G2", "G3", "G10", "G15"),
        "R3" to listOf("G1", "G3", "G5", "G9", "G12"),
        "R4" to listOf("G1", "G7", "G8", "G16"),
        "R5" to listOf("G3", "G6", "13", "G14", "G15", "G16"),
        )
    private val subGoal = mapOf<String, String>(
        "R1" to "A1",
        "R2" to "A2",
        "R3" to "A3",
        "R4" to "A4",
        "R5" to "R5"
    )
    private val queue = hashMapOf<String, List<String>>()
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHasilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val listGejala = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(EXTRA, Premis::class.java)
        } else {
            intent.getParcelableExtra<Premis>(EXTRA)
        }
        Log.d("Hasil", listGejala.toString())
        if (listGejala != null) {
            for ((index, value) in listGejala.premis.withIndex()) {
                binding.tvGejalaContent.append("${index+1}. ${value.name} \n")
            }
        }
//        binding.tvGejalaContent.text = premis?.listGejala?.get(0)?.name ?: "text"
        binding.btnBackHome.setOnClickListener {
            val toHome = Intent(this, MainActivity::class.java)
            toHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(toHome)
        }


        Log.d("isi queue", queue.toString())

    }

    companion object {
        const val EXTRA = "extra"
    }
}