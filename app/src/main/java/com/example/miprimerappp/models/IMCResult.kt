package com.example.miprimerappp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IMCResult(

    val type: String,
    val result: Double

) : Parcelable