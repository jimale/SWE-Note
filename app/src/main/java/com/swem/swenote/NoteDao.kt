package com.swem.swenote

import androidx.room.Dao
import androidx.room.Delete
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

    @Query("DELETE from note where id =:noteID ")
    fun deleteNote(noteID: Int)

    @Insert
    fun saveNote(note: Note)
}