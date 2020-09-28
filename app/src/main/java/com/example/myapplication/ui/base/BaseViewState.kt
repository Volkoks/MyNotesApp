package com.example.myapplication.ui.base

import com.example.myapplication.data.Note

open class BaseViewState<T>(val data: T, val error: Throwable?) {
}