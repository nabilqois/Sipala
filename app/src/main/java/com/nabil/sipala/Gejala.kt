package com.nabil.sipala

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Gejala(
    val id: String,
    val name: String
) : Parcelable

@Parcelize
data class Premis(
    val premis: List<Gejala>
) : Parcelable

data class Rule(
    val Rid: String,
    val kondisi: List<String>,
    val hasil: String
)