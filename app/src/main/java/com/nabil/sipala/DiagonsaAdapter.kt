package com.nabil.sipala

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.checkbox.MaterialCheckBox
import com.nabil.sipala.databinding.RowGejalaBinding
import kotlinx.android.synthetic.main.row_gejala.view.*

class DiagonsaAdapter(private val listGejala: ArrayList<Gejala>) : RecyclerView.Adapter<GejalaHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GejalaHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RowGejalaBinding.inflate(inflater, parent, false)
        return GejalaHolder(binding)
    }

    override fun onBindViewHolder(holder: GejalaHolder, position: Int) {
        val gejala = listGejala[position]
        holder.bind(gejala)
//        holder.itemView.card_diagnosa_gejala.setOnClickListener {
//            onItemClickCallback.onItemClickCallback(listGejala[holder.adapterPosition])
//        }
        holder.itemView.cb_diagnosa_gejala.setOnClickListener {
            val isChecked = (it as MaterialCheckBox).isChecked
            if (isChecked) {
                Log.d("isChecked", "Dicek ${listGejala[holder.adapterPosition]}")
            }
            onItemClickCallback.onItemClickCallback(listGejala[holder.adapterPosition])
        }
    }

    override fun getItemCount() = listGejala.size

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClickCallback(data: Gejala)
    }
}

class GejalaHolder(private val binding: RowGejalaBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(gejala: Gejala) {
        binding.apply {
            cbDiagnosaGejala.text = gejala.name
        }
    }
}