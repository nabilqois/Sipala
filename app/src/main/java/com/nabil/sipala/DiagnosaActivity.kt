package com.nabil.sipala

import android.content.Intent
import android.graphics.BlendMode
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.shape.MaterialShapeDrawable
import com.nabil.sipala.databinding.ActivityDiagnosaBinding

class DiagnosaActivity : AppCompatActivity() {
    private val premis = mutableListOf<Gejala>()
    private lateinit var binding: ActivityDiagnosaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagnosaBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.appbar. = MaterialShapeDrawable.createWithElevationOverlay(this);

        val diagnosaAdapter = DiagonsaAdapter(ListGejala.all)
        val linearLayoutManager = LinearLayoutManager(this)
        binding.rvDiagnosa.apply {
            layoutManager = linearLayoutManager
            adapter = diagnosaAdapter
            diagnosaAdapter.setOnItemClickCallback(object : DiagonsaAdapter.OnItemClickCallback {
                override fun onCheckedCallback(data: Gejala) {
                    Log.d("Ditekan", "berhasil ditekan")
                    Toast.makeText(this@DiagnosaActivity, "berhasil", Toast.LENGTH_SHORT).show()
                    premis.add(data)
                    Log.d("listdiagnosa", premis.toString())
                }

                override fun onUncheckedCallback(data: Gejala) {
                    Toast.makeText(this@DiagnosaActivity, "Dihapus", Toast.LENGTH_SHORT).show()
                    premis.remove(data)
                    Log.d("listdiagnosa", premis.toString())
                }

            })
        }

        binding.btnDiagnosa.setOnClickListener {
            Log.d("btn_diagnosa", premis.toString())
            val premisAll = Premis(premis)
            val toHasil = Intent(this, HasilActivity::class.java)
            toHasil.putExtra(HasilActivity.EXTRA, premisAll)

            if (premis.isEmpty()) {
                Toast.makeText(this, "Harap pilih gejala terlebih dahulu", Toast.LENGTH_SHORT).show()
            } else {
                startActivity(toHasil)
            }

        }

//        binding.rvDiagnosa.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}