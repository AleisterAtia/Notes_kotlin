package com.fadhiil2010.bukucatatan.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.fadhiil2010.bukucatatan.Note
import com.fadhiil2010.bukucatatan.R

class NoteAdapter(
    private var notes: List<Note>,
    context: Context
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(){

    class NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview) {
        val  judulNote : TextView = itemview.findViewById(R.id.tvNotesJudul)
        val  isiNote : TextView = itemview.findViewById(R.id.tvNoteIsi)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_notes,parent,false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        val noteItem = notes[position]
        holder.judulNote.text = noteItem.Title
        holder.isiNote.text = noteItem.content
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun refreshData(newNotes : List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}
