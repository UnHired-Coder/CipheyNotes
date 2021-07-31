package com.unhiredcoder.cipheynotes.fragments.fragmentNotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.unhiredcoder.cipheynotes.R
import com.unhiredcoder.cipheynotes.modals.TextNote

class FragmentViewNote : Fragment() {
    private lateinit var deviceId: String

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_view_note, container, false)
        deviceId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            .toString()
        val note: TextNote = arguments?.getParcelable<TextNote>("Note")!!
        view.findViewById<TextView>(R.id.tv_view_note_key).apply {
            "Key: ${note.key?.drop(deviceId.length + 1)}".also { text = it }
        }
        view.findViewById<TextView>(R.id.tv_view_note_content).apply {
            text = note.content
        }
        return view
    }
}