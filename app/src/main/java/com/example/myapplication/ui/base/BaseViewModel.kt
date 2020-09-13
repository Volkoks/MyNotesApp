package com.example.myapplication.ui.base

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.Note
import com.example.myapplication.ui.newNote.NewNoteFragment

open class BaseViewModel<T, S : BaseViewState<T>> : ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()
    open fun getViewState(): LiveData<S> = viewStateLiveData

    fun initNoteFragment(note: Note): NewNoteFragment {
        var fragment = NewNoteFragment()
        var bundle = Bundle()
        bundle.putParcelable("note", note)
        fragment.arguments = bundle

        return fragment
    }
}