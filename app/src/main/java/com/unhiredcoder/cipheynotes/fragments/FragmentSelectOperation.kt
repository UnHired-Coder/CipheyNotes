package com.unhiredcoder.cipheynotes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.unhiredcoder.cipheynotes.R

class FragmentSelectOperation : Fragment() {

    companion object {
        const val TAG = "tag_operation"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_select_operation, container, false)
        view.findViewById<Button>(R.id.btn_add_note).apply {
            setOnClickListener {
                val action =
                    FragmentSelectOperationDirections.actionFragmentSelectOperationToAddNotesFragment()
                findNavController().navigate(action)
            }
        }

        view.findViewById<Button>(R.id.btn_find_note).apply {
            setOnClickListener {
                val action =
                    FragmentSelectOperationDirections.actionFragmentSelectOperationToFragmentFindNotes()
                findNavController().navigate(action)
            }
        }

        return view
    }
}
