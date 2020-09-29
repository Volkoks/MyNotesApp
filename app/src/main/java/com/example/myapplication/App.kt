package com.example.myapplication

import android.app.Application
import com.example.myapplication.data.di.appModule
import com.example.myapplication.data.di.authModule
import com.example.myapplication.data.di.mainModule
import com.example.myapplication.data.di.newNoteModule
import org.koin.android.ext.android.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule, authModule, mainModule, newNoteModule))
    }
}