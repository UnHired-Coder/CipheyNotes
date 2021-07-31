package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.addNotes

import androidx.lifecycle.viewModelScope
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common.RepositoryNotes
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test

class ViewModelAddNotesTest {
    private lateinit var viewModel: ViewModelAddNotes

    companion object {
        const val DEVICE_ID = "DEVICE_ID"
    }

    @Before
    fun setup() {
        viewModel = ViewModelAddNotes(RepositoryNotes(), DEVICE_ID)
    }

    @Test
    fun `test add note`(){
        viewModel.content.value = ""
        viewModel.saveAndUploadNote()
    }


}