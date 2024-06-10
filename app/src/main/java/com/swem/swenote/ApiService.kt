package com.swem.swenote

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("atu.php")
    fun newNote(
        @Field("title") title: String,
        @Field("body") body: String
    ): Call<NoteResponse>
}