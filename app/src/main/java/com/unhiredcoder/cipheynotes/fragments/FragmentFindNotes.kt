package com.unhiredcoder.cipheynotes.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.unhiredcoder.cipheynotes.R
import com.unhiredcoder.cipheynotes.databinding.FragmentFindNotesBinding
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common.RepositoryNotes
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.findNotes.FactoryViewModelFindNotes
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.findNotes.ViewModelFindNotes
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FragmentFindNotes : Fragment() {

    lateinit var binding: FragmentFindNotesBinding

    @Inject
    lateinit var repositoryNotes: RepositoryNotes
    lateinit var viewModel: ViewModelFindNotes
    private lateinit var deviceId: String

    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_find_notes, container, false)
        val view: View = binding.root


        deviceId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            .toString()

        viewModel = ViewModelProvider(
            this,
            FactoryViewModelFindNotes(repositoryNotes = repositoryNotes, deviceId = deviceId)
        ).get(ViewModelFindNotes::class.java)

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