package com.example.myapplication.ui.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.newNote.NewNoteFragment

open class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()
    open fun getViewState(): LiveData<S> = viewStateLiveData

    fun initNoteFragment(note: Note): NewNoteFragment {
        val fragment = NewNoteFragment()
        val bundle = Bundle()
        bundle.putString("note", note.id)
        fragment.arguments = bundle
        return fragment
    }
}