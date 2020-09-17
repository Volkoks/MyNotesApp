package com.example.myapplication.ui.newNote

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_new_note.*
import java.util.*

class NewNoteFragment : BaseFragment<Note?, NewNoteViewState>() {
    private var note: Note? = null

    override val layoutRes = R.layout.fragment_new_note

    override val viewModel: NewNoteViewModel by lazy {
        ViewModelProviders.of(this).get(NewNoteViewModel::class.java)
    }

    override fun renderData(data: Note?) {
        this.note = data
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
        note = arguments?.getParcelable("note")
        note?.id?.let { id ->
            viewModel.load(id)
        } ?: let {
            editText_title.hint = "Заголовок.НОВАЯ ЗАМЕТКА"
        }

        save_note_materialButton.setOnClickListener(saveNote)
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
                viewModel.save(it)
            }


        }
    }


}