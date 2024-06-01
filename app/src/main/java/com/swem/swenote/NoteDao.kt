package com.swem.swenote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface NoteDao {

    @Query("SELECT * from note")
    fun getNoteList(): List<Note>

    @Query("SELECT * FROM note where id =:noteId")
    fun getNoteById(noteId: Int): Note

    @Update
    fun updateNote(note: Note)

    @Insert
    fun saveNote(note: Note)
}