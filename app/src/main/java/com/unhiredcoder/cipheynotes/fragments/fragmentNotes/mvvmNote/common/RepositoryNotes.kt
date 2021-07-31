package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common

import com.unhiredcoder.cipheynotes.modals.TextNote
import com.unhiredcoder.cipheynotes.networks.NotesApiService

class RepositoryNotes {

    suspend fun uploadNote(note: TextNote) =
        NotesApiService.notesInstance.uploadNote(key = note.key!!, note = note)

    suspend fun getNote(key: String) = NotesApiService.notesInstance.getNoteWithKey(key)
}
