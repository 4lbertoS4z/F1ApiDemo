package com.example.f1api.ui.drivers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.f1api.R
import com.example.f1api.databinding.DriverListBinding


class DriversAdapter (var drivers: List<Driver>) :
    RecyclerView.Adapter<DriversAdapter.DriversViewHolder>() {

    inner class DriversViewHolder(private val binding: DriverListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(driver: Driver) {
            binding.nameTextView.text = "Nombre: \n${driver.nombre}"
            binding.equipTextView.text = "Escuderia: \n${driver.escudería}"
            binding.yearTextView.text = "Edad:\n ${driver.edad.toString()}"
            binding.winsTextView.text = "Victorias: \n ${driver.victorias.toString()}"
            binding.podiumTextView.text = "Podios: \n${driver.podios.toString()}"
            binding.poleTextView.text = "Poles: \n${driver.poles.toString()}"
            Glide.with(binding.nationalityImageView.context).load(driver.nacionalidad).placeholder(R.drawable.cardsnitch).into(binding.nationalityImageView)
            Glide.with(binding.imageView.context).load(driver.image).placeholder(R.drawable.cardsnitch).into(binding.imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriversViewHolder {
        val binding = DriverListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriversViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriversViewHolder, position: Int) {
        val driver = drivers[position]
        holder.bind(driver)

    }

    override fun getItemCount() = drivers.size
}