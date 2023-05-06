package com.example.f1api.ui.drivers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f1api.databinding.FragmentDriversBinding
import com.example.f1api.databinding.FragmentTeamsBinding
import com.example.f1api.ui.teams.TeamsAdapter
import com.example.f1api.ui.teams.TeamsViewModel

class DriversFragment : Fragment() {

    private var _binding: FragmentDriversBinding? = null
    private lateinit var viewModel: DriversViewModel
    private lateinit var adapter: DriversAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDriversBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DriversViewModel::class.java)
        viewModel.getDriversList()
        adapter = DriversAdapter(emptyList())

        binding.teamRecycler.adapter = adapter
        binding.teamRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.drivers.observe(viewLifecycleOwner) { drivers ->
            adapter.drivers = drivers
            adapter.notifyDataSetChanged()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}