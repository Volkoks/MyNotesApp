package com.example.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Note
import com.example.myapplication.data.repository.NoteRepository

class MainFragmentViewModel : ViewModel(){

   var myNotes: MutableLiveData<List<Note>> = MutableLiveData()

    init {
        myNotes.value = NoteRepository.list
    }



}


