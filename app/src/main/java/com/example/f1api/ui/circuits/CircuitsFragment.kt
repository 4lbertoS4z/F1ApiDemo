package com.example.f1api.ui.circuits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f1api.databinding.FragmentCircuitsBinding
import com.example.f1api.databinding.FragmentDriversBinding
import com.example.f1api.ui.drivers.DriversAdapter
import com.example.f1api.ui.drivers.DriversViewModel


class CircuitsFragment : Fragment() {
    private var _binding: FragmentCircuitsBinding? = null
    private lateinit var viewModel: CircuitsViewModel
    private lateinit var adapter: CircuitsAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircuitsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CircuitsViewModel::class.java)
        viewModel.getCircuitsList()
        adapter = CircuitsAdapter(emptyList())

        binding.circuitRecycler.adapter = adapter
        binding.circuitRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.circuit.observe(viewLifecycleOwner) { circuit ->
            adapter.circuit = circuit
            adapter.notifyDataSetChanged()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}