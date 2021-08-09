package com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.findNotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.di.DeviceId
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.notes.common.RepositoryNotes
import com.unhiredcoder.cipheynotes.modals.TextNote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelFindNotes @Inject constructor(
    private val repositoryNotes: RepositoryNotes,
    @DeviceId private val deviceId: String
) : ViewModel() {
    val key: MutableLiveData<String> = MutableLiveData(null)
    val result: MutableLiveData<TextNote> = MutableLiveData(null)
    val status: MutableLiveData<String> = MutableLiveData(null)

    fun findNote() {
        result.value = null
        status.value = "Loading.."
        if (key.value == null || key.value.toString() == "") {
            status.value = "Error: key can not be empty!"
        } else {
            viewModelScope.launch {
                try {
                    val response =
                        repositoryNotes.getNote(deviceId + "_" + key.value)
                    withContext(Dispatchers.Main) {
                        if (response.isSuccessful && response.code() == 200) {
                            if (response.body() == null) {
                                status.value = "Error: No note with key '${key.value}' found!"
                            } else {
                                status.value = null
                                result.value = response.body()
                            }
                        } else {
                            status.value = "Error: ${response.message()}  ${response.code()}"
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        status.value =
                            "Error: Something went wrong, check your internet connection!"
                    }
                }
                // success
            }
        }
    }
}