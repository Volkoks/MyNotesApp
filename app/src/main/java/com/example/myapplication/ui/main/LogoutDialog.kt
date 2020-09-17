package com.example.myapplication.ui.main

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class LogoutDialog() : DialogFragment() {
    companion object{

        fun createInstance(onLogout:(()->Unit))=LogoutDialog().apply {
            this.onLogout = onLogout
        }
    }

    var onLogout: (() -> Unit)? = null

    override fun onCreateDialog(savedInstanceState: Bundle?) = AlertDialog.Builder(context)
        .setTitle("ВЫХОД")
        .setMessage("Вы уверены в том что хотите выйти?")
        .setPositiveButton("ДА") { dialog, with -> onLogout?.invoke() }
        .setNegativeButton("Нет") { dialog, with -> dismiss() }
        .create()
}