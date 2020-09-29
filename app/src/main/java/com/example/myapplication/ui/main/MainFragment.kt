package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.R
import com.example.myapplication.data.entity.Note
import com.example.myapplication.ui.base.BaseFragment
import com.example.myapplication.ui.base.BaseViewModel
import com.example.myapplication.ui.newNote.NewNoteFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.koin.android.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment<List<Note>?, MainViewState>() {
    override val layoutRes = R.layout.fragment_main

    override val model: MainFragmentViewModel by viewModel()

    lateinit var adapter: MainFragmentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        FAB_add_new_note.setOnClickListener { initNewNote() }


        recycler_view_main_fragment.layoutManager = GridLayoutManager(activity, 2)

        adapter = MainFragmentAdapter {
            activity?.supportFragmentManager!!.beginTransaction()
                .replace(R.id.fragment_container, model.initNoteFragment(it))
                .addToBackStack("NoteFragment")
                .commit()
        }

        recycler_view_main_fragment.adapter = adapter

//        viewModel.viewState().observe(this, Observer { t ->
//            t?.let { list ->
//                adapter.thisNotes = t.notes
//                recycler_view_main_fragment.adapter = adapter
//            }
//        })


    }

    private fun initNewNote() {
        activity?.supportFragmentManager!!.beginTransaction()
            .replace(R.id.fragment_container, NewNoteFragment()).addToBackStack("NoteFragment")
            .commit()
    }

    override fun renderData(data: List<Note>?) {
        data?.let {
            adapter.thisNotes = it
            recycler_view_main_fragment.adapter = adapter
        }
    }

}