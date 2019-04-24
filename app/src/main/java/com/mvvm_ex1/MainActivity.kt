package com.mvvm_ex1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvm_ex1.adapter.Recyclerview_adapter
import com.mvvm_ex1.db.Note
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var noteViewModel : NoteViewModel

    var count = 110

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview: RecyclerView = findViewById(R.id.recyclerview)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)

        val noteAdapter = Recyclerview_adapter(this)
        recyclerview.adapter = noteAdapter

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)
        noteViewModel.getAllNotes()?.observe(this, object: Observer<MutableList<Note>>{
            override fun onChanged(t: MutableList<Note>?) {
                noteAdapter.setNotes(t!!)
                recyclerview.scrollToPosition(noteAdapter.itemCount -1)
                Toast.makeText(this@MainActivity, "onChanged", Toast.LENGTH_SHORT).show()
            }

        })

        add_btn.setOnClickListener {
            count += 1
            noteViewModel.insert(Note(count, "Titie10", "Ddddd"))
        }

    }

}
