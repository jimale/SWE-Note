package com.swem.swenote

import androidx.room.Entity
import androidx.room.PrimaryKey

data class NoteResponse(
    val error: Boolean,
    val message: String
)
