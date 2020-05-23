package com.sundeep1501.weatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sundeep1501.weatherforecast.backend.MetaWeatherService
import com.sundeep1501.weatherforecast.backend.data.MWLocation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LocationViewModel : ViewModel() {

    private var call:Call<List<MWLocation>>? = null
    private val locations = MutableLiveData<List<MWLocation>>()

    fun getLocations(): LiveData<List<MWLocation>> {
        return locations
    }

    fun search(txt: String) {
        // cancel previous request and continue with latest search text
        if(call != null){
            call!!.cancel()
        }

        call = MetaWeatherService.instance.searchLocation(txt)
        call?.enqueue(object:Callback<List<MWLocation>>{
            override fun onFailure(call: Call<List<MWLocation>>, t: Throwable) {
                when {
                    call.isCanceled -> {
                        Log.i(TAG, "request cancelled")
                    }
                    t is IOException -> {
                        Log.i(TAG, "No internet")
                    }
                    else -> {
                        Log.i(TAG, "unknown error")
                    }
                }
                locations.postValue(null)
            }
            override fun onResponse(
                call: Call<List<MWLocation>>,
                response: Response<List<MWLocation>>
            ) {
                locations.postValue(response.body())
            }
        })
    }

    companion object{
        val TAG = LocationViewModel::class.java.simpleName
    }

}