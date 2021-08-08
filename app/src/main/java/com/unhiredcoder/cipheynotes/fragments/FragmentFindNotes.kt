package com.unhiredcoder.cipheynotes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.unhiredcoder.cipheynotes.R
import com.unhiredcoder.cipheynotes.databinding.FragmentFindNotesBinding
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.findNotes.ViewModelFindNotes
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentFindNotes : Fragment() {

    lateinit var binding: FragmentFindNotesBinding
    private val viewModel: ViewModelFindNotes by viewModels()

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_notes, container, false)
        val view: View = binding.root

        val deviceId =
            Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)

        viewModel.deviceId = deviceId
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.btnFindNote.apply {
            setOnClickListener {
                viewModel.findNote()
            }
        }
        observeModal()
        return view
    }

    private fun observeModal() {
        viewModel.result.observe(viewLifecycleOwner, Observer { note ->
            if (note != null) {
                val action =
                    FragmentFindNotesDirections.actionFragmentFindNotesToFragmentViewNote(note)
                findNavController().navigate(action)
                viewModel.result.value = null
            }
        })
    }

}