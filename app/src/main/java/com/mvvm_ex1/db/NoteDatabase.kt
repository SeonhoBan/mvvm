package com.mvvm_ex1.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask
import androidx.sqlite.db.SupportSQLiteDatabase

const val ROOM_VERSION = 1

@Database(entities = [Note::class], version = ROOM_VERSION)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase? {
            if(instance == null) {
                synchronized(Note::class) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        NoteDatabase::class.java, "note.db")
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback())
                        .build()
                }
            }
            return instance
        }

        private fun roomCallback() : RoomDatabase.Callback = object: RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDBAsyncTask(instance!!).execute()
            }
        }
    }

    private class PopulateDBAsyncTask() : AsyncTask<Void, Void, Void>() {
        constructor(db: NoteDatabase): this() {
            noteDao = db.noteDao()
        }
        private var noteDao : NoteDao? = null

        override fun doInBackground(vararg params: Void?): Void? {
            noteDao?.insert(Note(1, "Title1", "Description 1"))
            noteDao?.insert(Note(2, "Title2", "Description 1"))
            noteDao?.insert(Note(3, "Title3", "Description 1"))
            noteDao?.insert(Note(4, "Title4", "Description 1"))
            return null
        }

    }

}
