package com.example.f1api.ui.circuits

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1api.api.ApiService
import com.example.f1api.model.Circuit
import com.example.f1api.settings.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CircuitsViewModel : ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val f1Api: ApiService = retrofit.create(ApiService::class.java)

    private val _circuit = MutableLiveData<List<Circuit>>()
    val circuit: LiveData<List<Circuit>> = _circuit

    fun getCircuitsList(){
        f1Api.getCircuits().enqueue(object : Callback<List<Circuit>> {
            override fun onResponse(call: Call<List<Circuit>>, response: Response<List<Circuit>>) {
                if (response.isSuccessful) {
                    _circuit.value = response.body()
                } else {
                    Log.e("API error", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Circuit>>, t: Throwable) {
                Log.e("API error", "Error: ${t.message}")
            }
        })
    }

}