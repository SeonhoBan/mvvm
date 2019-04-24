package com.mvvm_ex1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mvvm_ex1.db.Note
import com.mvvm_ex1.R

class Recyclerview_adapter(private val context: Context)
    : androidx.recyclerview.widget.RecyclerView.Adapter<Recyclerview_adapter.Holder>() {

    private var notes : MutableList<Note> = mutableListOf()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.note_item, p0, false)

        return Holder(view)
    }

    override fun onBindViewHolder(p0: Holder, p1: Int) {
        val view = p0 as? Holder

        view?.let {
            it.onBind(notes[p1])
        }
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun setNotes(notes: MutableList<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }


    inner class Holder(view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {
        private val title = view.findViewById(R.id.title) as TextView
        private val discription = view.findViewById(R.id.description) as TextView

        fun onBind(item: Note) {
            title.text = item.title
            discription.text = item.title
        }
    }
}
