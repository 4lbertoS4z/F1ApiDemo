package com.example.f1api.ui.drivers

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1api.api.ApiService
import com.example.f1api.settings.BASE_URL
import com.example.f1api.ui.teams.Team
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DriversViewModel : ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val f1Api: ApiService = retrofit.create(ApiService::class.java)
    // Define la propiedad students
    private val _drivers = MutableLiveData<List<Driver>>()
    val drivers: LiveData<List<Driver>> = _drivers
    fun getDriversList(){
        f1Api.getDrivers().enqueue(object : Callback<List<Driver>> {
            override fun onResponse(call: Call<List<Driver>>, response: Response<List<Driver>>) {
                if (response.isSuccessful) {
                    _drivers.value = response.body()
                } else {
                    Log.e("API error", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Driver>>, t: Throwable) {
                Log.e("API error", "Error: ${t.message}")
            }
        })
    }
}