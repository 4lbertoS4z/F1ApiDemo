package com.example.f1api.ui.circuits

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1api.R
import com.example.f1api.databinding.CircuitListBinding
import com.example.f1api.model.Circuit


class CircuitsAdapter (var circuit: List<Circuit>) :
    RecyclerView.Adapter<CircuitsAdapter.CircuitViewHolder>() {

    inner class CircuitViewHolder(private val binding: CircuitListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(circuit: Circuit) {
            binding.nameCircuitTextView.text = "Nombre: \n${circuit.nombre}"
            binding.lapsTextView.text = "Nº vueltas: \n${circuit.vueltas.toString()}"
            binding.distenceTextView.text = "Longitud:\n ${circuit.longitud.toString()}"
            binding.dateTextView.text = "Fechas: \n ${circuit.fechas}"
            binding.lastWinnerTextView.text = "Último ganador: \n${circuit.ultimo_ganador}"
            Glide.with(binding.imageView.context).load(circuit.image).placeholder(R.drawable.cardsnitch).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CircuitViewHolder {
        val binding = CircuitListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CircuitViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CircuitViewHolder, position: Int) {
        val circuitInfo = circuit[position]
        holder.bind(circuitInfo)

    }

    override fun getItemCount() = circuit.size
}