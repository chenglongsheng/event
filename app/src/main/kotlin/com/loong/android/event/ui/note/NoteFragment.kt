package com.loong.android.event.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.loong.android.event.databinding.FragmentNoteBinding

class NoteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

}