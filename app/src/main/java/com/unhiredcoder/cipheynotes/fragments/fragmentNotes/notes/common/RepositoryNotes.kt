package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.common

import com.unhiredcoder.cipheynotes.modals.TextNote
import com.unhiredcoder.cipheynotes.networks.NotesApiService
import javax.inject.Inject

class RepositoryNotes @Inject constructor() {

    suspend fun uploadNote(note: TextNote) =
        NotesApiService.notesInstance.uploadNote(key = note.key!!, note = note)

    suspend fun getNote(key: String) = NotesApiService.notesInstance.getNoteWithKey(key)
}
