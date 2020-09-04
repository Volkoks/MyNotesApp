package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ui.adapter.NotesMainFragmentAdapter
import com.example.myapplication.ui.newNote.NewNoteFragment
import kotlinx.android.synthetic.main.fragment_main.*
import com.example.myapplication.viewmodel.MainFragmentViewModel as MainFragmentViewModel


class MainFragment : Fragment() {

    lateinit var viewModel: MainFragmentViewModel
    var adapter: NotesMainFragmentAdapter = NotesMainFragmentAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        FAB_add_new_note.setOnClickListener(View.OnClickListener { initNewNote() })

        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)

        recycler_view_main_fragment.layoutManager = GridLayoutManager(activity, 2)
        recycler_view_main_fragment.adapter = adapter
        viewModel.myNotes.observe(this, Observer { t ->
            t?.let { list ->
                adapter.thisNotes = t.notes
            }
        })


    }


    private fun initNewNote() {
        var newNoteFragment = NewNoteFragment()
        var ft: FragmentTransaction = activity?.supportFragmentManager!!.beginTransaction()
        ft.replace(R.id.fragment_container, newNoteFragment)
        ft.addToBackStack("newNote")
        ft.commit()
    }

}