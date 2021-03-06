package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.findNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.common.RepositoryNotes

@Suppress("UNCHECKED_CAST")
class FactoryViewModelFindNotes(
    private val repositoryNotes: RepositoryNotes,
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelFindNotes(repositoryNotes, deviceId = "null") as T
    }
}