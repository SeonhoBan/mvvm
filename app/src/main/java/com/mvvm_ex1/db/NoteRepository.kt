package com.mvvm_ex1.db

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

class NoteRepository() {
    private var noteDao : NoteDao? = null
    private var allNote : LiveData<MutableList<Note>>? = null

    constructor(application : Application): this() {
        val database : NoteDatabase = NoteDatabase.getInstance(application)!!
        noteDao = database.noteDao()
        allNote = noteDao?.getAllnotes()
    }

    fun insert(note: Note) {
        NoteAsyncTask(noteDao!!, QUERY_TYPE.INSERT).execute(note)
    }

    fun update(note: Note){
        NoteAsyncTask(noteDao!!, QUERY_TYPE.UPDATE).execute(note)
    }

    fun delete(note: Note){
        NoteAsyncTask(noteDao!!, QUERY_TYPE.DELETE).execute(note)
    }

    fun deleteAllNotes(){
        NoteAsyncTask(noteDao!!, QUERY_TYPE.DELETEALL).execute()
    }

    fun getAllNotes() : LiveData<MutableList<Note>>? {
        return allNote
    }


    private inner class NoteAsyncTask() : AsyncTask<Note, Void, Void>() {
        constructor(noteDao: NoteDao, type: QUERY_TYPE):this() {
            this.noteDao = noteDao
            this.type = type
        }
        private var noteDao : NoteDao? = null
        private lateinit var type : QUERY_TYPE

        override fun doInBackground(vararg params: Note?): Void? {
            when(type) {
                QUERY_TYPE.INSERT -> noteDao!!.insert(params[0]!!)
                QUERY_TYPE.UPDATE -> noteDao!!.update(params[0]!!)
                QUERY_TYPE.DELETE -> noteDao!!.delete(params[0]!!)
            }
            return null
        }

    }
    enum class QUERY_TYPE {
        INSERT, UPDATE, DELETE, DELETEALL, GETALL;
    }
}
