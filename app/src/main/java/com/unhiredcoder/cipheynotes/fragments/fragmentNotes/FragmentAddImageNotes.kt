package com.unhiredcoder.cipheynotes.fragments.fragmentNotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.unhiredcoder.cipheynotes.R

class FragmentAddImageNotes : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_image_notes, container, false)
    }
}