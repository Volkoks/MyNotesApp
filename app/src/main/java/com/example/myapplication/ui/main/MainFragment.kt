package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.ui.NewNoteFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainFragment : Fragment() {

    lateinit var fabAddNewNote: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater.inflate(R.layout.fragment_main, container, false)
        fabAddNewNote = view.findViewById(R.id.FAB_add_new_note)
        fabAddNewNote.setOnClickListener(View.OnClickListener {
            initNewNote()
        })
        return view
    }

    private fun initNewNote() {
        var newNoteFragment: NewNoteFragment = NewNoteFragment()
        var ft: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        ft.replace(R.id.fragment_container, newNoteFragment)
        ft.addToBackStack("newNote")
        ft.commit()
    }

}