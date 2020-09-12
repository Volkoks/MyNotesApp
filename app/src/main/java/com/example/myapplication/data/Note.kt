package com.example.myapplication.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Note(
    var id: String = "",
    var title: String = "",
    var description: String = "",
    var color: Color = Color.YELLOW,
    var lastChanged: Date = Date()
) : Parcelable {

    enum class Color {
        WHITE,
        YELLOW,
        GREEN,
        BLUE,
        RED,
        VIOLET,
    }
}
