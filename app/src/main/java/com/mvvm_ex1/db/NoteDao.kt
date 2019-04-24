package com.mvvm_ex1.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note): Void

    @Update
    fun update(note: Note): Void

    @Delete
    fun delete(note: Note): Void

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    fun getAllnotes() : LiveData<MutableList<Note>>
}
