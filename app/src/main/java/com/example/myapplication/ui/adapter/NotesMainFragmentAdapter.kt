package com.example.myapplication.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Note
import kotlinx.android.synthetic.main.card_view_note.view.*

class NotesMainFragmentAdapter(notes: List<Note>) :
    RecyclerView.Adapter<NotesMainFragmentAdapter.ViewHolder>() {

    var thisNotes: List<Note> = notes

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_view_note, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(thisNotes, position)
    }

    override fun getItemCount() = thisNotes.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: List<Note>, position: Int) = with(itemView) {
            title_card_view.text = note[position].title
            description_card_view.text = note[position].discription
        }

    }
}
