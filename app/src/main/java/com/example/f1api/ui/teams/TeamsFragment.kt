package com.example.f1api.ui.teams

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.f1api.databinding.FragmentTeamsBinding

class TeamsFragment : Fragment() {

    private var _binding: FragmentTeamsBinding? = null
    private lateinit var viewModel: TeamsViewModel
    private lateinit var adapter: TeamsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTeamsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamsViewModel::class.java)
        viewModel.getStudentList()
        adapter = TeamsAdapter(emptyList())

        binding.teamRecycler.adapter = adapter
        binding.teamRecycler.layoutManager = LinearLayoutManager(context)

        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            adapter.teams = teams
            adapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
