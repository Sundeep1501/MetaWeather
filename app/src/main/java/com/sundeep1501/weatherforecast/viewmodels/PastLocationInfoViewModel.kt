package com.sundeep1501.weatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sundeep1501.weatherforecast.backend.MetaWeatherService
import com.sundeep1501.weatherforecast.backend.data.MWWeather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PastLocationInfoViewModel() : ViewModel() {

    private var callPastLocationInfo: Call<List<MWWeather>>? = null
    private val pastLocationInfo = MutableLiveData<List<MWWeather>>()

    fun getPastLocationInfo(): LiveData<List<MWWeather>> {
        return pastLocationInfo
    }

    fun getLocationDetails(woeid: Int, year: Int, month: Int, day: Int) {
        // cancel previous request and continue with latest search text
        if (callPastLocationInfo != null) {
            callPastLocationInfo!!.cancel()
        }

        callPastLocationInfo =
            MetaWeatherService.instance.getWeatherForDay(woeid, year, month, day)
        callPastLocationInfo?.enqueue(object : Callback<List<MWWeather>> {
            override fun onFailure(call: Call<List<MWWeather>>, t: Throwable) {
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
            }

            override fun onResponse(
                call: Call<List<MWWeather>>,
                response: Response<List<MWWeather>>
            ) {
                pastLocationInfo.postValue(response.body())
            }
        })
    }

    companion object {
        val TAG = PastLocationInfoViewModel::class.java.simpleName
    }

}