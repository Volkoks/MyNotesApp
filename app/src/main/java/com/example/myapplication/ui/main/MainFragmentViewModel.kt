package com.example.myapplication.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.data.Note
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.main.MainViewState
import com.example.myapplication.ui.newNote.NewNoteFragment

class MainFragmentViewModel : ViewModel() {

    private var myNotes: MutableLiveData<MainViewState> = MutableLiveData()


    init {
        NoteRepository.getNotes().observeForever{ listNotes ->
            listNotes?.let { listNotes ->
                myNotes.value = myNotes.value?.copy(notes = listNotes) ?: MainViewState(listNotes)
            }

        }

    }

    fun viewState(): LiveData<MainViewState> = myNotes

    fun initNoteFragment(note: Note): NewNoteFragment{
        var fragment = NewNoteFragment()
        var title = note.title
        var discription = note.discription
        var bundle = Bundle()
        bundle.putString("title", title)
        bundle.putString("description",discription)
        fragment.arguments = bundle

        return fragment
    }
}







