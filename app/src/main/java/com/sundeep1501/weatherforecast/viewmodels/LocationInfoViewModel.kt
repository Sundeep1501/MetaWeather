package com.sundeep1501.weatherforecast.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sundeep1501.weatherforecast.backend.MetaWeatherService
import com.sundeep1501.weatherforecast.backend.data.MWLocationInfo
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class LocationInfoViewModel() : ViewModel() {

    private var callLocationInfo: Call<MWLocationInfo>? = null
    private val locationInfo = MutableLiveData<MWLocationInfo>()
    private val progressBar = MutableLiveData<Boolean>()

    fun getProgressBar(): LiveData<Boolean> {
        return progressBar
    }

    fun getLocationInfo(): LiveData<MWLocationInfo> {
        return locationInfo
    }

    fun getLocationDetails(mwLocationID: Int) {
        // cancel previous request and continue with latest search text
        if (callLocationInfo != null) {
            callLocationInfo!!.cancel()
        }

        progressBar.postValue(true);
        callLocationInfo = MetaWeatherService.instance.getLocationInfo(mwLocationID)
        callLocationInfo?.enqueue(object : Callback<MWLocationInfo> {
            override fun onFailure(call: Call<MWLocationInfo>, t: Throwable) {
                progressBar.postValue(false)
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
                call: Call<MWLocationInfo>,
                response: Response<MWLocationInfo>
            ) {
                progressBar.postValue(false)
                locationInfo.postValue(response.body())
            }
        })
    }

    companion object {
        val TAG = LocationInfoViewModel::class.java.simpleName
    }

}