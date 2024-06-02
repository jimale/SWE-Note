package com.swem.swenote

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room

class NewNoteActivity : AppCompatActivity() {

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_note)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Initialize database
        database = MyDatabase.invoke(this)

        val saveButton: Button = findViewById(R.id.save_btn)
        val deleteButton: Button = findViewById(R.id.delete_btn)
        saveButton.setOnClickListener {
            saveNote()
        }
        deleteButton.setOnClickListener {
            deleteNote()
        }


        val noteId = intent.getIntExtra("note_id", -1)
        if (noteId != -1) {
            displayNoteData()
            saveButton.text = "Save changes"
            deleteButton.visibility = View.VISIBLE
        }
    }


    private fun saveNote() {
        val noteTitle: EditText = findViewById(R.id.note_title_et)
        val noteBody: EditText = findViewById(R.id.note_body_et)

        Thread {
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

    private fun displayNoteData() {
        val noteTitle: EditText = findViewById(R.id.note_title_et)
        val noteBody: EditText = findViewById(R.id.note_body_et)
        val updateButton: Button = findViewById(R.id.save_btn)

        Thread {

            val noteId = intent.getIntExtra("note_id", 0)

            //Fetch note data by using noteID
            val noteData = database.noteDao().getNoteById(noteId)

            runOnUiThread {
                //Display note data
                noteTitle.setText(noteData.title)
                noteBody.setText(noteData.body)
                updateButton.setOnClickListener { updateNote() }
            }

        }.start()

    }

    private fun updateNote() {
        val noteTitle: EditText = findViewById(R.id.note_title_et)
        val noteBody: EditText = findViewById(R.id.note_body_et)

        Thread {

            val noteId = intent.getIntExtra("note_id", 0)

            //Update note
            val note = Note(
                id = noteId,
                title = noteTitle.text.toString(),
                body = noteBody.text.toString()
            )

            database.noteDao().updateNote(note)

            runOnUiThread {
                Toast.makeText(this, "Successfully updated", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }


        }.start()
    }

    private fun deleteNote() {
        Thread {

            val noteId = intent.getIntExtra("note_id", 0)

            database.noteDao().deleteNote(noteId)

            runOnUiThread {
                Toast.makeText(this, "Successfully deleted", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }.start()
    }
}