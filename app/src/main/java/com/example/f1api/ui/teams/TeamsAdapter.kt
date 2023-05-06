package com.example.f1api.ui.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1api.R
import com.example.f1api.databinding.TeamListBinding
import com.example.f1api.model.Team

class TeamsAdapter (var teams: List<Team>) :
    RecyclerView.Adapter<TeamsAdapter.TeamViewHolder>() {

    inner class TeamViewHolder(private val binding: TeamListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            binding.chiefTextView.text = "Jefe: \n${team.jefe}"
            binding.yearTextView.text = "Año entrada:\n ${team.año_entrada.toString()}"
            binding.championsWinTextView.text = "Campeonatos ganados: \n ${team.campeonatos_ganados.toString()}"
            Glide.with(binding.nationalityImageView.context).load(team.nacionalidad).placeholder(R.drawable.cardsnitch).into(binding.nationalityImageView)
            Glide.with(binding.imageView.context).load(team.image).placeholder(R.drawable.cardsnitch).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val teamInfo = teams[position]
        holder.bind(teamInfo)

    }

    override fun getItemCount() = teams.size
}