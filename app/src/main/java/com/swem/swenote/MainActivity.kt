package com.swem.swenote

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fetchNoteList()

        findViewById<FloatingActionButton>(R.id.new_note_btn).setOnClickListener {
            startActivity(Intent(this, NewNoteActivity::class.java))
        }
    }

    private fun fetchNoteList() {
        Thread {

            val database = Room.databaseBuilder(
                applicationContext,
                MyDatabase::class.java, "my_db"
            )
                .fallbackToDestructiveMigration()
                .build()

            //Get note list from the database
            val noteList = database.noteDao().getNoteList()

            //Get back to the main thread
            runOnUiThread {
                val adapter = NoteAdapter(noteList)
                val recyclerView: RecyclerView = findViewById(R.id.note_recyclerView)

                recyclerView.adapter = adapter
            }

        }.start()
    }
}