package com.unhiredcoder.cipheynotes.fragments.fragmentNotes

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.provider.Settings
import android.view.*
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.unhiredcoder.cipheynotes.R
import com.unhiredcoder.cipheynotes.databinding.FragmentAddTextNotesBinding
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.addNotes.FactoryViewModelAddNotes
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.common.RepositoryNotes
import com.unhiredcoder.cipheynotes.fragments.fragmentNotes.mvvmNote.addNotes.ViewModelAddNotes
import com.unhiredcoder.cipheynotes.modals.TextNote

class FragmentAddTextNotes : Fragment() {

    private lateinit var binding: FragmentAddTextNotesBinding
    private lateinit var viewModel: ViewModelAddNotes
    private lateinit var repositoryNotes: RepositoryNotes
    private lateinit var deviceId: String


    @SuppressLint("HardwareIds")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_text_notes, container, false)
        val view: View = binding.root

        deviceId = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)
            .toString()
        repositoryNotes = RepositoryNotes()
        viewModel =
            ViewModelProvider(
                this,
                FactoryViewModelAddNotes(repositoryNotes = repositoryNotes, deviceId = deviceId)
            ).get(
                ViewModelAddNotes::class.java
            )

        binding.noteViewModel = viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)
        observeModel()
        return view
    }

    private fun showCopyKeyDialogue(note: TextNote) {

        val dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.share_copy_secret_key_dialog)

        val key = note.key?.drop(deviceId.length + 1)

        dialog.apply {
            findViewById<TextView>(R.id.tv_secret_key).text = key
            findViewById<Button>(R.id.btn_copy_key).setOnClickListener {
                copyToClipBoard(key!!)
                dialog.hide()
            }
            show()
        }
    }

    private fun copyToClipBoard(key: String) {
        val clipBard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("key_copy", key)
        clipBard.setPrimaryClip(clip)
        Toast.makeText(context, "Key copied to clip board.", Toast.LENGTH_LONG).show()
    }

    private fun observeModel() {
        viewModel.result.observe(viewLifecycleOwner, { note ->
            if (note != null) {
                showCopyKeyDialogue(note)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.tools_add_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btn_text_note_save -> {
                viewModel.saveAndUploadNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}