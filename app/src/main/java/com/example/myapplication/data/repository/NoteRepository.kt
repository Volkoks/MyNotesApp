package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.Note
import java.util.*

object NoteRepository {

    private val notesLiveData = MutableLiveData<List<Note>>()


    private val listNote = mutableListOf(
        Note(UUID.randomUUID().toString(),"Первая заметка", "Что то очень важное", 0xfff06292.toInt()),
        Note(UUID.randomUUID().toString(),"Вторая заметка", "tot krot", 0xffFFFF6E40.toInt()),
        Note(UUID.randomUUID().toString(),"3 заметка", "tot krot", 0xffFF11FF00.toInt()),
        Note(UUID.randomUUID().toString(),"4 заметка", "tot krot", 0xffFFD000FF.toInt()),
        Note(UUID.randomUUID().toString(),"5 заметка", "tot krot", 0xffFFD64AAF.toInt()),
        Note(UUID.randomUUID().toString(),"6 заметка", "tot krot", 0xffFF4902ED.toInt()),
        Note(UUID.randomUUID().toString(),"7 заметка", "tot krot", 0xffF0ED0245.toInt()),
        Note(UUID.randomUUID().toString(),"8 заметка", "tot krot", 0xffF002ED9B.toInt()),
        Note(UUID.randomUUID().toString(),"9 заметка", "tot krot", 0xffD30AED02.toInt()),
        Note(UUID.randomUUID().toString(),"10 заметка", "tot krot", 0xffFFC6FF00.toInt())
    )

    init {
        notesLiveData.value = listNote
    }

    fun getNotes(): LiveData<List<Note>> {
        return notesLiveData
    }

    fun saveNote(note: Note) {
        replaceNote(note)
        notesLiveData.value = listNote
    }

    fun replaceNote(note: Note) {
        for (i in listNote.indices) {
            if (listNote[i].id === note.id) {
                listNote[i] == note
            }
        }
        listNote.add(note)
    }
}