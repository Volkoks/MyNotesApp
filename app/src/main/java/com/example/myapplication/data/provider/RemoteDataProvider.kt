package com.example.myapplication.data.provider

import androidx.lifecycle.LiveData
import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.entity.User
import com.example.myapplication.data.model.NoteResult

interface RemoteDataProvider {
    fun subscribeToAllNote(): LiveData<NoteResult>
    fun getNoteByID(id: String): LiveData<NoteResult>
    fun saveNote(note: Note): LiveData<NoteResult>
    fun getCurrentUser():LiveData<User?>
}