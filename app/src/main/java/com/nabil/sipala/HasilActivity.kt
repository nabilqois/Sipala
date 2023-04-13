package com.nabil.sipala

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.nabil.sipala.databinding.ActivityHasilBinding



class HasilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHasilBinding
    private val premis = mutableListOf<String>()
    private val rule = mutableListOf<Rule>(
        Rule("R1", listOf("G1", "G2", "G3", "G4"), "A1"),
        Rule("R2", listOf("G1", "G2", "G3", "G10", "G15"), "A2"),
        Rule("R3", listOf("G1", "G3", "G5", "G9", "G12"), "A3"),
        Rule("R4", listOf("G1", "G2", "G7", "G8"), "A4"),
        Rule("R5", listOf("G2", "G3", "G6", "G13", "G14", "G15"), "A5"),
    )

    private val penyakit = mapOf<String, Penyakit>(
        "A1" to ListPenyakit.all[0],
        "A2" to ListPenyakit.all[1],
        "A3" to ListPenyakit.all[2],
        "A4" to ListPenyakit.all[3],
        "A5" to ListPenyakit.all[4],
    )
    private val queue = mutableListOf<Rule>()
    private val hasil = mutableListOf<Rule>()

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
                premis.add(value.id)
            }
        }
        Log.d("Premis", premis.toString())

        binding.btnBackHome.setOnClickListener {
            val toHome = Intent(this, HomeActivity::class.java)
            toHome.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(toHome)
        }


        Log.d("isi queue", queue.toString())
        var completed = false
        while(!completed) {
            completed = true
            var premisLength = premis.size
            Log.d("algorithm", premis.toString())
            Log.d("algorithm", "==============================================")
            Log.d("algorithm", "premisLength awal: $premisLength")

            insertNewQueue(premis, rule, queue)
            Log.d("algorithm", "Hasil Queue : ")

            if (queue.isEmpty()) {
                break
            }
            queue.forEach {
                Log.d("algorithm", "Rid : ${it.Rid} ")
            }

            Log.d("algorithm", "")
            val queueSorted = queue.sortedBy { it.hasil }
            Log.d("algorithm", "queueSorted : $queueSorted")
            Log.d("algorithm", "\npremis size : ${premis.size}")
            Log.d("algorithm", premis.toString())
            insertNewPremis(premis, queueSorted as MutableList<Rule>, hasil)

            if (premisLength != premis.size) {
                Log.d("algorithm", "premisLength tidak sama")
                Log.d("algorithm", "")
                completed = false

            } else {
                Log.d("algorithm", "premisSize Akhir : ${premis.size}")
                Log.d("algorithm", "")
                Log.d("algorithm", "Queue Akhir: ")
                queueSorted.forEach {
                    Log.d("algorithm", "${it.hasil} ")
                }
                Log.d("algorithm", "\n")
                Log.d("algorithm", "Hasil :")
                if (hasil.isNotEmpty()) {
                    hasil.forEach {
                        Log.d("algorithm", "${it.hasil} ")
                    }
                } else {
                    Log.d("algorithm", "Tidak ada hasil yang cocok")
                }
            }

        }

        if (hasil.isEmpty()) {
            binding.tvPenyakit.text = "Tidak ada penyakit yang cocok"
            Log.d("algorithm", "hasil.isEmpty()")
            binding.tvPenyebabTitle.visibility = View.INVISIBLE
            binding.tvPengobatanTitle.visibility = View.INVISIBLE
            binding.tvPencegahanTitle.visibility = View.INVISIBLE
        } else {

            val penyakitAkhir = penyakit[hasil.last().hasil]
            binding.tvPenyakit.text = penyakitAkhir?.nama
            binding.tvPengertian.text = penyakitAkhir?.pengertian

            val penyebab = StringBuilder()
            penyakitAkhir?.penyebab?.forEach {
                penyebab.appendLine("- $it")
            }
            binding.tvPenyebabList.text = penyebab

            val pengobatan = StringBuilder()
            penyakitAkhir?.pengobatan?.forEach {
                pengobatan.appendLine("- $it")
            }
            binding.tvPengobatanList.text = pengobatan

            val pencegahan = StringBuilder()
            penyakitAkhir?.pencegahan?.forEach {
                pencegahan.appendLine("- $it")
            }
            binding.tvPencegahanList.text = pencegahan
        }

    }


    companion object {
        const val EXTRA = "extra"
    }

    private fun insertNewQueue(premis: MutableList<String>, rule: MutableList<Rule>, queue: MutableList<Rule>){
        premis.forEach { premisValue ->
            Log.d("algorithm", "-----")
            Log.d("algorithm", "-----insertNewQueue")
            Log.d("algorithm", "premisValue check : $premisValue")
            rule.forEach { r  ->
                r.kondisi.forEach { kondisi ->
                    if (kondisi == premisValue) {
                        Log.d("algorithm", "-----")
                        Log.d("algorithm", "rule yg sama : ${r.Rid}")
                        Log.d("algorithm", "dgn premisValue : $premisValue")
                        Log.d("algorithm", "-----")

                        if (!queue.contains(Rule(r.Rid, r.kondisi, r.hasil))) {
                            queue.add(r)
                        }
                    }
                }
            }
        }

    }

    private fun insertNewPremis(premis: MutableList<String>, queue: MutableList<Rule>, hasil: MutableList<Rule>) {
        queue.forEach { rule1 ->
            Log.d("algorithm", "-----insertNewPremis")
            Log.d("algorithm", "queue rule check: ${rule1.Rid}")

            if (premis.containsAll(rule1.kondisi)) {
                Log.d("algorithm", "semua kondisi sama")
                Log.d("algorithm", "rule1.kondisi : ${rule1.kondisi}")
                Log.d("algorithm", "rule1.hasil : ${rule1.hasil}")

                if (!premis.contains(rule1.hasil)) {
                    Log.d("algorithm", "tidak mengandung ${rule1.hasil}")
                    Log.d("algorithm", "${rule1.hasil} ditambahkan ke premis")
                    premis.add(rule1.hasil)
                    hasil.add(rule1)
                    Log.d("algorithm", "")
                }
            } else {
                Log.d("algorithm", "tidak ada yg sama persis")
                Log.d("algorithm", "")
            }

        }
        Log.d("algorithm", "Hasil premis : ")
        Log.d("algorithm", premis.toString())
    }
}