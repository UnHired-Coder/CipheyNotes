package com.unhiredcoder.cipheynotes.networks

import com.unhiredcoder.cipheynotes.interfaces.INotesApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NotesApiService {
    var notesInstance: INotesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(INotesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        notesInstance = retrofit.create(INotesApi::class.java)
    }

}