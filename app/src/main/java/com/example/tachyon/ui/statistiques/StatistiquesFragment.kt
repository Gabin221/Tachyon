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

    lateinit var customGraphView: CustomGraphView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentStatistiquesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // val textView: TextView = binding.textStatistiques

        customGraphView = binding.root.findViewById(R.id.customGraphView) // Utilisez la variable globale

        // Exemple de données de fonction
        val functionValues = mutableListOf<Float>()
        for (i in -10..10) {
            val y = i * i.toFloat() // Par exemple, y = x^2
            functionValues.add(y)
        }

        // Exemple de données de nuage de points
        val scatterPoints = listOf(
            Pair(-4f, 16f),
            Pair(-3f, 9f),
            Pair(-2f, 4f),
            Pair(-1f, 1f),
            Pair(0f, 0f),
            Pair(1f, 1f),
            Pair(2f, 4f),
            Pair(3f, 9f),
            Pair(4f, 16f)
        )

        customGraphView.setFunctionValues(functionValues)
        customGraphView.setScatterPoints(scatterPoints)

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
