package com.example.myapplication.data.di


import com.example.myapplication.data.provider.FireStoreProvider
import com.example.myapplication.data.provider.RemoteDataProvider
import com.example.myapplication.data.repository.NoteRepository
import com.example.myapplication.ui.authScreen.AuthViewModel
import com.example.myapplication.ui.main.MainFragmentViewModel
import com.example.myapplication.ui.newNote.NewNoteViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {
    single { FirebaseFirestore.getInstance() }
    single { FirebaseAuth.getInstance() }
    single<RemoteDataProvider> { FireStoreProvider(get(), get()) }
    single { NoteRepository(get()) }
}
val authModule = module {
    viewModel { AuthViewModel(get()) }
}
val mainModule = module {
    viewModel { MainFragmentViewModel(get()) }
}
val newNoteModule = module {
    viewModel { NewNoteViewModel(get()) }
}