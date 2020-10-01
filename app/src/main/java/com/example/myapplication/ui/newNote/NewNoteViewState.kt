package com.example.myapplication.ui.newNote

import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.base.BaseViewState

class NewNoteViewState(data: Data = Data(), e: Throwable? = null) :
    BaseViewState<NewNoteViewState.Data>(data, e) {
    data class Data(val isDeleted: Boolean = false, val note: Note? = null)
}