package com.sundeep1501.weatherforecast.backend

import com.sundeep1501.weatherforecast.backend.data.MWLocation
import com.sundeep1501.weatherforecast.backend.data.MWLocationInfo
import com.sundeep1501.weatherforecast.backend.data.MWWeather
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {

    companion object {
        // singleton instance
        val instance: MetaWeatherService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://www.metaweather.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(MetaWeatherService::class.java)
        }
    }

    @GET("location/search")
    fun searchLocation(@Query("query") txt: String): Call<List<MWLocation>>

    @GET("location/{woeid}")
    fun getLocationInfo(@Path("woeid") woeid: Long): Call<MWLocationInfo>

    @GET("location/{woeid}/{yyyy}/{mm}/{dd}")
    fun getWeatherForDay(
        @Path("woeid") woeid: Long,
        @Path("yyyy") year: Int,
        @Path("mm") month: Int,
        @Path("dd") day: Long
    ): Call<List<MWWeather>>
}