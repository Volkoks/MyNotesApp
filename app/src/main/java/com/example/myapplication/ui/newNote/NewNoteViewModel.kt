package com.example.myapplication.ui.newNote


import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.model.NoteResult
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.base.BaseViewModel

class NewNoteViewModel : BaseViewModel<Note?, NewNoteViewState>() {

    private var pandingNote: Note? = null

    fun save(note: Note) {
        pandingNote = note
        pandingNote?.let {
            NoteRepository.saveNote(it)
        }

    }

    fun load(noteId: String) {
        NoteRepository.getNoteByID(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value = NewNoteViewState(note = result.data as? Note)
                }
                is NoteResult.Error ->{
                    viewStateLiveData.value = NewNoteViewState(e = result.error)
                }
            }

        }


    }


}


