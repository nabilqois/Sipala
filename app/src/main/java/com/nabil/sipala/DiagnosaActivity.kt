package com.nabil.sipala

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nabil.sipala.databinding.ActivityDiagnosaBinding

class DiagnosaActivity : AppCompatActivity() {
    private val premis = mutableListOf<Gejala>()
    private lateinit var binding: ActivityDiagnosaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val diagnosaAdapter = DiagonsaAdapter(ListGejala.all)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvDiagnosa.apply {
            layoutManager = linearLayoutManager
            adapter = diagnosaAdapter
            diagnosaAdapter.setOnItemClickCallback(object : DiagonsaAdapter.OnItemClickCallback {
                override fun onItemClickCallback(data: Gejala) {
                    Log.d("Ditekan", "berhasil ditekan")
                    Toast.makeText(this@DiagnosaActivity, "berhasil", Toast.LENGTH_SHORT).show()
                    premis.add(data)
                }

            })
        }

        binding.btnDiagnosa.setOnClickListener {
            Log.d("btn_diagnosa", premis.toString())
            val premisAll = Premis(premis)
            val toHasil = Intent(this, HasilActivity::class.java)
            toHasil.putExtra("EXTRA", premisAll)
            startActivity(toHasil)
        }

        binding.rvDiagnosa.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}