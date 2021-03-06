package com.example.myapplication.ui.newNote

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import com.example.myapplication.R
import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_note.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class NewNoteFragment : BaseFragment<NewNoteViewState.Data, NewNoteViewState>() {
    private var note: Note? = null

    override val layoutRes = R.layout.fragment_new_note

    override val model: NewNoteViewModel by viewModel()

    override fun renderData(data: NewNoteViewState.Data) {
        if (data.isDeleted) activity?.supportFragmentManager!!.popBackStackImmediate()

        this.note = data.note
        note?.let {
            editText_title.setText(note?.title)
            editTextML_description.setText(note?.description)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteId = arguments?.getString("note")
        noteId?.let { id ->
            model.load(id)
        } ?: let {
            editText_title.hint = "Заголовок.НОВАЯ ЗАМЕТКА"
        }

        save_note_materialButton.setOnClickListener(saveNote)
        delete_note.setOnClickListener(deleteNote)
    }


    private val saveNote = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            if (editText_title.text == null || editTextML_description.text.length < 2) return

            note = note?.copy(
                title = editText_title.text.toString(),
                description = editTextML_description.text!!.toString()
            ) ?: Note(
                UUID.randomUUID().toString(), editText_title.text.toString(),
                editTextML_description.text.toString(), Note.Color.RED
            )
            note?.let {
                model.save(it)
            }


        }
    }
    private val deleteNote = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            AlertDialog.Builder(activity)
                .setMessage("Вы точно уверены?")
                .setPositiveButton("ДА") { dialog, which -> model.deleteNote() }
                .setNegativeButton("НЕТ") { dialog, which -> dialog.dismiss() }
                .show()


        }

    }


}