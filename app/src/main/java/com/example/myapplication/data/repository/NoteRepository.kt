package com.example.myapplication.data.repository

import com.example.myapplication.data.Note

object NoteRepository {

    var list: List<Note> = listOf(
        Note("Первая заметка", "Что то очень важное", 0),
        Note("Вторая заметка", "tot krot", 0),
        Note("3 заметка", "tot krot", 0),
        Note("4 заметка", "tot krot", 0),
        Note("5 заметка", "tot krot", 0),
        Note("6 заметка", "tot krot", 0),
        Note("7 заметка", "tot krot", 0)
    )
}