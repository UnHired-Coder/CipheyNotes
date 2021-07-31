package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.findNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common.RepositoryNotes

class FactoryViewModelFindNotes(
    private val repositoryNotes: RepositoryNotes,
    private val deviceId: String
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelFindNotes(repositoryNotes, deviceId) as T
    }
}