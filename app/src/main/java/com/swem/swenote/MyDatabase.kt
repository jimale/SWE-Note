package com.swem.swenote

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class MyDatabase : RoomDatabase() {
   abstract fun noteDao(): NoteDao

   companion object {
      @Volatile
      private var instance: MyDatabase? = null
      private val LOCK = Any()

      operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
         instance ?: buildDatabase(context).also { instance = it}
      }

      private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
         MyDatabase::class.java, "my_db")
         .fallbackToDestructiveMigration()
         .build()
   }
}