package com.example.myapplication.ui.newNote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.R
import com.example.myapplication.data.Note
import kotlinx.android.synthetic.main.fragment_new_note.*
import java.util.*

class NewNoteFragment : Fragment() {

    private var note: Note? = null
    private var viewModel = NewNoteViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_new_note, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        note = arguments?.getParcelable("note")
        viewModel = ViewModelProviders.of(this).get(NewNoteViewModel::class.java)

        save_note_materialButton.setOnClickListener(saveNote)

        editText_title.setText(note?.title)
        editTextML_description.setText(note?.description)
    }

    private val saveNote = object : View.OnClickListener {
        override fun onClick(p0: View?) {
            if (editText_title.text == null || editTextML_description.text.length < 2) return

            note = note?.copy(
                title = editText_title.text.toString(),
                description = editTextML_description.text.toString()
            ) ?: Note(UUID.randomUUID().toString(),editText_title.text.toString() + "случайная",
            editTextML_description.text.toString() + "случайная",0)

            note?.let {
                viewModel.save(it)
            }
        }
    }
}