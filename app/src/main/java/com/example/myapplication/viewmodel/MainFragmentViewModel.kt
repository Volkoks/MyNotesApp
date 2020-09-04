package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.MainViewState

class MainFragmentViewModel : ViewModel() {

    var myNotes: MutableLiveData<MainViewState> = MutableLiveData()

    init {
        myNotes.value = MainViewState(NoteRepository.list)
    }

    fun viewState(): LiveData<MainViewState> = myNotes

}


