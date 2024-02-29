package com.example.tachyon.ui.codes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.tachyon.R
import com.example.tachyon.databinding.FragmentCodesBinding
import com.google.android.material.tabs.TabLayout

class CodesFragment : Fragment() {

    private var _binding: FragmentCodesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCodesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val tabLayout = root.findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = root.findViewById<ViewPager>(R.id.viewPager)

        viewPager.adapter = TabsAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)

        return root
    }

    class TabsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> PythonFragment()
                1 -> KotlinFragment()
                2 -> CFragment()
                3 -> CPlusPlusFragment()
                4 -> ShellFragment()
                5 -> WebFragment()
                6 -> LaTeXFragment()
                // Ajoutez d'autres fragments pour d'autres onglets ici
                else -> throw IllegalArgumentException("Invalid tab position")
            }
        }

        override fun getCount(): Int {
            return 7 // Nombre total d'onglets
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "Python"
                1 -> "Kotlin"
                2 -> "C"
                3 -> "C++"
                4 -> "Shell"
                5 -> "Web"
                6 -> "LaTeX"
                // Ajoutez d'autres titres pour d'autres onglets ici
                else -> throw IllegalArgumentException("Invalid tab position")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}