package com.unhiredcoder.cipheynotes.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import com.unhiredcoder.cipheynotes.R

class FragmentAddNotes : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_notes, container, false)
        view.findViewById<Button>(R.id.btn_add_text_note).apply {
            setOnClickListener {
                val action =
                    FragmentAddNotesDirections.actionAddNotesFragmentToFragmentAddTextNotes()
                findNavController().navigate(action)
            }
        }

        // view.findViewById<Button>(R.id.btn_add_image_note).apply {
        //    setOnClickListener {
        //        val action =
        //            FragmentAddNotesDirections.actionAddNotesFragmentToFragmentAddImageNotes()
        //        findNavController().navigate(action)
        //    }
        // }

        return view
    }
}