package com.example.myapplication.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.Note
import kotlinx.android.synthetic.main.card_view_note.view.*

class MainFragmentAdapter(val onItemClick: ((Note) -> Unit)? = null) :
    RecyclerView.Adapter<MainFragmentAdapter.ViewHolder>() {

    var thisNotes: List<Note> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.card_view_note, parent, false)
    )


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(thisNotes[position])
    }

    override fun getItemCount() = thisNotes.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) = with(itemView) {
            title_card_view.text = note.title
            description_card_view.text = note.description
            setBackgroundColor(note.color)

            itemView.setOnClickListener {
                onItemClick?.invoke(note)
            }
        }


    }

}
