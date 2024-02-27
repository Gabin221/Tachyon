package com.example.tachyon.ui.codes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
//import android.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.tachyon.R
import com.example.tachyon.databinding.FragmentCodesBinding
import com.google.android.material.tabs.TabLayout

class CodesFragment : Fragment() {

    private var _binding: FragmentCodesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var pager: ViewPager // creating object of ViewPager
    private lateinit var tab: TabLayout  // creating object of TabLayout
    private lateinit var bar: Toolbar    // creating object of ToolBar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCodesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // set the references of the declared objects above
        pager = binding.root.findViewById(R.id.viewPager)
        tab = binding.root.findViewById(R.id.tabs)
        bar = binding.root.findViewById(R.id.toolbar)

        // To make our toolbar show the application
        // we need to give it to the ActionBar
        (requireActivity() as AppCompatActivity).setSupportActionBar(bar)

        val adapter = ViewPagerAdapter(childFragmentManager)

        // add fragment to the list
        adapter.addFragment(PythonFragment(), "Python")
        adapter.addFragment(CPlusPlusFragment(), "C++")
        adapter.addFragment(KotlinFragment(), "Kotlin")

        // Adding the Adapter to the ViewPager
        pager.adapter = adapter

        // bind the viewPager with the TabLayout.
        tab.setupWithViewPager(pager)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}