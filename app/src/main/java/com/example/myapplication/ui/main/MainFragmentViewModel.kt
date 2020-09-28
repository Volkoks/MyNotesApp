package com.example.myapplication.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.data.model.NoteResult
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.main.MainViewState
import com.example.myapplication.ui.newNote.NewNoteFragment

class MainFragmentViewModel : BaseViewModel<List<Note>?, MainViewState>() {


    private val notesObserver = Observer<NoteResult> { result ->
        result ?: return@Observer
        when (result) {
            is NoteResult.Success<*> -> {
                viewStateLiveData.value = MainViewState(notes = result.data as? List<Note>)
            }
            is NoteResult.Error -> {
                viewStateLiveData.value = MainViewState(error = result.error)
            }
        }

    }
    private var noteRepository = NoteRepository.getNotes()

    init {
        viewStateLiveData.value = MainViewState()
        noteRepository.observeForever(notesObserver)
    }

    override fun onCleared() {
        noteRepository.removeObserver(notesObserver)
        super.onCleared()
    }

}







