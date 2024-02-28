package com.example.tachyon.ui.statistiques

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tachyon.R
import com.example.tachyon.databinding.FragmentStatistiquesBinding

class StatistiquesFragment : Fragment() {

    private var _binding: FragmentStatistiquesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatistiquesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textStatistiques

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
