package com.example.myapplication.ui.newNote

import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.base.BaseViewState

class NewNoteViewState(val note: Note? = null, e: Throwable? = null) :
    BaseViewState<Note?>(note, e)