package com.example.myapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    var id: String,
    var title: String,
    var discription: String,
    var color: Int,
    var lastChanged: Date = Date()
) : Parcelable {


}