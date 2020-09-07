package com.example.myapplication.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.ui.newNote.NewNoteFragment
import kotlinx.android.synthetic.main.fragment_main.*



class MainFragment : Fragment() {

    lateinit var viewModel: MainFragmentViewModel
    lateinit var adapter: MainFragmentAdapter

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

        FAB_add_new_note.setOnClickListener { initNewNote() }

        viewModel = ViewModelProviders.of(this).get(MainFragmentViewModel::class.java)

        recycler_view_main_fragment.layoutManager = GridLayoutManager(activity, 2)
        adapter = MainFragmentAdapter{
            var title = it.title
            var discription = it.discription
            var bundle = Bundle()
            bundle.putString("title", title)
            bundle.putString("description",discription)
            var fragment = NewNoteFragment()

            fragment.arguments = bundle

            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, fragment).addToBackStack("NoteFragment")
                .commit()

        }

        recycler_view_main_fragment.adapter = adapter

        viewModel.viewState().observe(this, Observer { t ->
            t?.let { list ->
                adapter.thisNotes = t.notes
                recycler_view_main_fragment.adapter = adapter
            }
        })


    }

    private fun initNewNote() {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, NewNoteFragment()).addToBackStack("NoteFragment")
            .commit()
    }

}