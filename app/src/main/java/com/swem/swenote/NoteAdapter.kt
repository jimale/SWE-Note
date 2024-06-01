package com.swem.swenote

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter(private val noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)

        return NoteViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {

        holder.display(noteList[position])
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return noteList.size
    }

    // Holds the views for adding it to image and text
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val noteTitle: TextView = itemView.findViewById(R.id.note_title_tv)

        fun display(note: Note) {
            noteTitle.text = note.title
            //noteDate.text = note.date

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, NewNoteActivity::class.java)
                intent.putExtra("note_id", note.id)
                itemView.context.startActivity(intent)
            }
        }
    }

}