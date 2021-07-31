package com.unhiredcoder.cipheynotes.modals

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TextNote(val key: String?, val content: String?):Parcelable
