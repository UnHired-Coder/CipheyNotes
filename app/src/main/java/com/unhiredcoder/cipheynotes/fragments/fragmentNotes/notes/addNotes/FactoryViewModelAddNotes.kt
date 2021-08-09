package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.addNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.common.RepositoryNotes

@Suppress("UNCHECKED_CAST")
class FactoryViewModelAddNotes(
    private val repositoryNotes: RepositoryNotes,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelAddNotes(repositoryNotes, deviceId = "null") as T
    }
}