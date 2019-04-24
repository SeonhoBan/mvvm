package com.mvvm_ex1

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.mvvm_ex1.db.Note
import com.mvvm_ex1.db.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private var repository : NoteRepository? = null
    private var allNotes : LiveData<MutableList<Note>>? = null

    init{
        repository = NoteRepository(application)
        allNotes = repository!!.getAllNotes()
    }

    fun insert(note: Note) {
        repository!!.insert(note)
    }

    fun update(note: Note) {
        repository!!.update(note)
    }

    fun delete(note: Note) {
        repository!!.delete(note)
    }

    fun getAllNotes() : LiveData<MutableList<Note>>? {
        return allNotes
    }
}
