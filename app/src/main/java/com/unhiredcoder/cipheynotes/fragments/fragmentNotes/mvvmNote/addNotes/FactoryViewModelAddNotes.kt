package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.addNotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common.RepositoryNotes

class FactoryViewModelAddNotes(
    private val repositoryNotes: RepositoryNotes,
    private val deviceId: String
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModelAddNotes(repositoryNotes, deviceId) as T
    }
}