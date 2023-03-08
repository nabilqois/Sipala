package com.nabil.sipala

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_diagnosa.*

class DiagnosaActivity : AppCompatActivity() {
    private val premis = arrayListOf<Gejala>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diagnosa)

        val diagnosaAdapter = DiagonsaAdapter(ListGejala.all)
        val linearLayoutManager = LinearLayoutManager(this)
        rv_diagnosa.apply {
            layoutManager = linearLayoutManager
            adapter = diagnosaAdapter
            diagnosaAdapter.setOnItemClickCallback(object : DiagonsaAdapter.OnItemClickCallback {
                override fun onItemClickCallback(data: Gejala) {
                    Log.d("Ditekan", "berhasil ditekan")
                    Toast.makeText(this@DiagnosaActivity, "berhasil", Toast.LENGTH_SHORT).show()
                    premis.add(data)
                }

            })
            btn_diagnosa.setOnClickListener {
                Log.d("btn_diagnosa", "berhasil ditekan")
                Toast.makeText(this@DiagnosaActivity, premis.toString(), Toast.LENGTH_LONG).show()
            }

        }

        rv_diagnosa.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
    }
}