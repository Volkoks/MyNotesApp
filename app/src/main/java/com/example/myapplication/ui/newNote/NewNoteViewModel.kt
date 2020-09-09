package com.example.myapplication.ui.newNote

import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Note
import com.example.myapplication.data.repository.NoteRepository

class NewNoteViewModel : ViewModel(){
    private var pandingNote: Note? = null

    fun save(note: Note){
        pandingNote = note
        pandingNote?.let {
            NoteRepository.saveNote(note)
        }
    }

}


