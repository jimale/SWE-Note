package com.swem.swenote

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class NewNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.save_btn)
        saveButton.setOnClickListener {
            saveNote()
        }

    }


    private fun saveNote() {
        val noteTitle: EditText = findViewById(R.id.note_title_et)
        val noteBody: EditText = findViewById(R.id.note_body_et)

        Thread {

            val database = Room.databaseBuilder(
                applicationContext,
                MyDatabase::class.java, "my_db"
            )
                .fallbackToDestructiveMigration()
                .build()

            //Save the note to the database
            val note = Note(
                title = noteTitle.text.toString(),
                body = noteBody.text.toString()
            )

            database.noteDao().saveNote(note)

            runOnUiThread {
                Toast.makeText(this, "Succussfully Saved", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }

        }.start()

    }
}