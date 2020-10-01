package com.example.myapplication.data.repository

import com.example.myapplication.data.entity.Note
import com.example.myapplication.data.provider.FireStoreProvider
import com.example.myapplication.data.provider.RemoteDataProvider

class NoteRepository(private val provider: RemoteDataProvider) {
    fun getNotes() = provider.subscribeToAllNote()
    fun saveNote(note: Note) = provider.saveNote(note)
    fun getNoteByID(id: String) = provider.getNoteByID(id)
    fun getCurrentUser() = provider.getCurrentUser()
    fun deleteNote(noteId: String) = provider.deleteNote(noteId)
}