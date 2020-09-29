package com.example.myapplication.ui.authScreen

import com.example.myapplication.data.errors.NoAuthExp
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.base.BaseViewModel

class AuthViewModel(val noteRepository: NoteRepository):  BaseViewModel<Boolean?,AuthViewState>() {

    fun requestUser(){
        noteRepository.getCurrentUser().observeForever {
            viewStateLiveData.value = it?.let {
                AuthViewState(authentication = true)
            }?:let {
                AuthViewState(error = NoAuthExp())
            }
        }
    }
}