package com.example.transconnect.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bus(
        val bId: String,
        val bKoridor: String,
        val bNomorbus: String,
        val bImagesrc:  Int,
        val bAvailseat: String,
        val bPassanger: String
) : Parcelable