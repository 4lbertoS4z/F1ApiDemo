package com.example.f1api.api

import com.example.f1api.settings.CIRCUIT
import com.example.f1api.settings.DRIVER
import com.example.f1api.settings.TEAM
import com.example.f1api.ui.circuits.Circuit
import com.example.f1api.ui.drivers.Driver
import com.example.f1api.ui.teams.Team
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(DRIVER)
    fun getDrivers(): Call<List<Driver>>

    @GET(TEAM)
    fun getTeams(): Call<List<Team>>

    @GET(CIRCUIT)
    fun getCircuits(): Call<List<Circuit>>
}