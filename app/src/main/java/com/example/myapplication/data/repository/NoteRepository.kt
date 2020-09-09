package com.example.myapplication.data.repository

import com.example.myapplication.data.Note

object NoteRepository {

    var list: List<Note> = listOf(
        Note("Первая заметка", "Что то очень важное", 0xfff06292.toInt()),
        Note("Вторая заметка", "tot krot", 0xffFFFF6E40.toInt()),
        Note("3 заметка", "tot krot", 0xffFF11FF00.toInt()),
        Note("4 заметка", "tot krot", 0xffFFD000FF.toInt()),
        Note("5 заметка", "tot krot", 0xffFFD64AAF.toInt()),
        Note("6 заметка", "tot krot", 0xffFF4902ED.toInt()),
        Note("7 заметка", "tot krot", 0xffF0ED0245.toInt()),
        Note("8 заметка", "tot krot", 0xffF002ED9B.toInt()),
        Note("9 заметка", "tot krot", 0xffD30AED02.toInt()),
        Note("10 заметка", "tot krot", 0xffFFC6FF00.toInt())
    )
}