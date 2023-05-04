package com.example.f1api.ui.classification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

import com.example.f1api.databinding.FragmentClassificationBinding



class ClassificationFragment : Fragment() {
    private var _binding: FragmentClassificationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(ClassificationViewModel::class.java)

        _binding = FragmentClassificationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textClassification
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}