package com.example.myapplication.data.repository

import com.example.myapplication.data.Note
import com.example.myapplication.data.provider.FireStoreProvider
import com.example.myapplication.data.provider.RemoteDataProvider

object NoteRepository {
private val provider: RemoteDataProvider = FireStoreProvider()

    fun getNotes()= provider.subscribeToAllNote()
    fun saveNote(note: Note) = provider.saveNote(note)
    fun getNoteByID(id: String)= provider.getNoteByID(id)
}