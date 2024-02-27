package com.example.tachyon.ui.cours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tachyon.databinding.FragmentCoursBinding

class CoursFragment : Fragment() {

    private var _binding: FragmentCoursBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoursBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCours
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}