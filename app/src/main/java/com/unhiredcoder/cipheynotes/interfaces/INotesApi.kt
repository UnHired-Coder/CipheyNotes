package com.unhiredcoder.cipheynotes.interfaces

import com.unhiredcoder.cipheynotes.modals.TextNote
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface INotesApi {
    companion object {
        const val BASE_URL = "https://cipheynotes-default-rtdb.firebaseio.com/"
    }

    @GET("notes/{key}.json")
    suspend fun getNoteWithKey(@Path("key") key: String): Response<TextNote>

    @PUT("notes/{key}.json")
    suspend fun uploadNote(@Path("key") key: String, @Body note: TextNote?): Response<TextNote>

}
