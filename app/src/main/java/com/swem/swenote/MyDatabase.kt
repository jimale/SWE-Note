package com.swem.swenote

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class MyDatabase : RoomDatabase() {
   abstract fun noteDao(): NoteDao
}