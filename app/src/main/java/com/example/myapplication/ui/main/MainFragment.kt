package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import com.example.myapplication.ui.newNote.NewNoteFragment
import kotlinx.android.synthetic.main.fragment_main.view.*


class MainFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        view.FAB_add_new_note.setOnClickListener(View.OnClickListener { initNewNote() })

        return view
    }


    private fun initNewNote() {
        var newNoteFragment = NewNoteFragment()
        var ft: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        ft.replace(R.id.fragment_container, newNoteFragment)
        ft.addToBackStack("newNote")
        ft.commit()
    }

}