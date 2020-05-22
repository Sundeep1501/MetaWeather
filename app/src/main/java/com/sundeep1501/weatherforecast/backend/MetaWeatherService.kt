package com.sundeep1501.weatherforecast.backend

import MWLocationInfo
import com.sundeep1501.weatherforecast.backend.data.MWLocation
import com.sundeep1501.weatherforecast.backend.data.MWWeather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetaWeatherService {
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