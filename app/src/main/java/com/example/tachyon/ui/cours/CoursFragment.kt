package com.example.tachyon.ui.cours

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tachyon.R
import com.example.tachyon.databinding.FragmentCoursBinding
import com.example.tachyon.ui.statistiques.CustomGraphView

class CoursFragment : Fragment() {

    private var _binding: FragmentCoursBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    lateinit var customGraphView: CustomGraphView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCoursBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textCours

        customGraphView = binding.root.findViewById(R.id.customGraphView) // Utilisez la variable globale

        // Exemple de données de fonction
        val xValues = linspace(-4f, 4f, 1000)

        val yValues = xValues.map { it * it }

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

        customGraphView.setFunctionValues(xValues, yValues)

        customGraphView.setScatterPoints(scatterPoints)

        return root
    }

    private fun linspace(start: Float, stop: Float, numPoints: Int): List<Float> {
        require(numPoints > 1) { "numPoints doit être supérieur à 1" }
        val step = (stop - start) / (numPoints - 1)
        return List(numPoints) { start + it * step }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}