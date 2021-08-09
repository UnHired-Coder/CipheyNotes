package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.addNotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.di.DeviceId
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.common.RepositoryNotes
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.util.genKey
import com.unhiredcoder.cipheynotes.modals.TextNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelAddNotes @Inject constructor(
    private val notesRepository: RepositoryNotes,
    @DeviceId private val deviceId: String
) :
    ViewModel() {

    val content: MutableLiveData<String> =
        MutableLiveData("Write here!")
    val status: MutableLiveData<String> = MutableLiveData(null)
    val result: MutableLiveData<TextNote> = MutableLiveData(null)

    private fun uploadNote(note: TextNote) {
        viewModelScope.launch {
            try {
                val response = notesRepository.uploadNote(note)
                if (response.isSuccessful && response.body() != null) {
                    status.value = "Successfully Uploaded!"
                    result.value = response.body()
                } else {
                    status.value = "Failed: " + response.message() + " " + response.code()
                }
            } catch (e: Exception) {
                status.value =
                    "Error: Something went wrong, check your internet connection!"
            } finally {
                status.value = null
            }
        }
    }

    fun saveAndUploadNote() {
        if (content.value.equals("") || status.value != null) {
            status.value = "Error: Note can not be empty!"
            return
        }
        status.value = "Loading.."
        val key = getKey()
        val note = TextNote(key, content = content.value.toString())
        clearNote()
        uploadNote(note)
    }

    private fun clearNote() {
        content.value = null
    }

    private fun getKey(): String {
        return deviceId.genKey()
    }
}