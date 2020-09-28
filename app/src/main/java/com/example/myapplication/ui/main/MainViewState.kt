package com.example.myapplication.ui.main

import com.example.myapplication.data.Note
import com.example.myapplication.ui.base.BaseViewState

class MainViewState(var notes: List<Note>? = null, error: Throwable? = null): BaseViewState<List<Note>?>(notes,error)