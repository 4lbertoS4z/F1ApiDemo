package com.example.f1api.ui.teams

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.f1api.api.ApiService
import com.example.f1api.model.Team
import com.example.f1api.settings.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TeamsViewModel : ViewModel() {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val f1Api: ApiService= retrofit.create(ApiService::class.java)
    // Define la propiedad teams
    private val _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>> = _teams

    fun getStudentList(){
        f1Api.getTeams().enqueue(object : Callback<List<Team>> {
            override fun onResponse(call: Call<List<Team>>, response: Response<List<Team>>) {
                if (response.isSuccessful) {
                    _teams.value = response.body()
                } else {
                    Log.e("API error", "Error: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<List<Team>>, t: Throwable) {
                Log.e("API error", "Error: ${t.message}")
            }
        })
    }

}