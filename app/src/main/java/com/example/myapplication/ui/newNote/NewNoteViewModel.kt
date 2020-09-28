package com.example.myapplication.ui.newNote


import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.model.NoteResult
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.base.BaseViewModel

class NewNoteViewModel(val noteRepository: NoteRepository) :
    BaseViewModel<NewNoteViewState.Data, NewNoteViewState>() {

    private val pandingNote: Note?
        get() = viewStateLiveData.value?.data?.note

    fun save(note: Note) {
        pandingNote?.let {
            NoteRepository.saveNote(it)
        }

    }

    fun load(noteId: String) {
        noteRepository.getNoteByID(noteId).observeForever { result ->
            result ?: return@observeForever
            when (result) {
                is NoteResult.Success<*> -> {
                    viewStateLiveData.value =
                        NewNoteViewState(NewNoteViewState.Data(note = result.data as? Note))
                }
                is NoteResult.Error -> {
                    viewStateLiveData.value = NewNoteViewState(e = result.error)
                }
            }
        }
    }

    fun deleteNote() {
        pandingNote?.let {
            noteRepository.deleteNote(it.id).observeForever { result ->
                result ?: return@observeForever
                when (result) {
                    is NoteResult.Success<*> -> viewStateLiveData.value =
                        NewNoteViewState((NewNoteViewState.Data(isDeleted = true)))
                    is NoteResult.Error -> viewStateLiveData.value =
                        NewNoteViewState(e = result.error)
                }

            }
        }
    }


}


