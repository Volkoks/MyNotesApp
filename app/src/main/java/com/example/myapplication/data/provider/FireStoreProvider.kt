package com.example.myapplication.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.Note
import com.example.myapplication.data.model.NoteResult
import com.google.firebase.firestore.FirebaseFirestore


class FireStoreProvider : RemoteDataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
    }

    private val TAG = FireStoreProvider::class.java.simpleName
    private val db = FirebaseFirestore.getInstance()
    private val noteReference = db.collection(NOTES_COLLECTION)


    override fun subscribeToAllNote(): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        noteReference.addSnapshotListener { snapshot, error ->
            error?.let {
                result.value = NoteResult.Error(error)
            } ?: let {
                snapshot?.let {
                    val notes = snapshot.documents.map {
                        it.toObject(Note::class.java)
                    }
                    result.value = NoteResult.Success(notes)
                }
            }
        }
        return result
    }

    override fun getNoteByID(id: String): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()
        noteReference.document(id).get().addOnSuccessListener {
            result.value = NoteResult.Success(it.toObject(Note::class.java))
        }
            .addOnFailureListener { result.value = NoteResult.Error(it) }
        return result
    }

    override fun saveNote(note: Note): LiveData<NoteResult> {
        val result = MutableLiveData<NoteResult>()

        noteReference.document(note.id).set(note)
            .addOnSuccessListener {
                Log.d(TAG, "Note $note save")
                result.value = NoteResult.Success(note)
            }.addOnFailureListener { p0 ->
                Log.d(TAG, "Note $note ERROR: ${p0.message}")
                result.value = NoteResult.Error(p0)
            }
        return result
    }
}