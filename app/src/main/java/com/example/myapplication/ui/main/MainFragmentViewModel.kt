package com.example.myapplication.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.main.MainViewState

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
}







