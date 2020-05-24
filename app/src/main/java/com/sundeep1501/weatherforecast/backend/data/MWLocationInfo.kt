package com.sundeep1501.weatherforecast.backend.data

import java.text.SimpleDateFormat
import java.util.*

data class MWLocationInfo(
    val consolidated_weather: List<MWWeather>,
    val time: String,
    val sun_rise: String,
    val sun_set: String,
    val timezone_name: String,
    val parent: MWParent,
    val sources: List<MWSource>,
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String,
    val timezone: String
) {

    private fun format(str: String): String {
        // 2020-05-23T19:25:41.686640-05:00
        // 2001-07-04T12:08:56.235-07:00
        // "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
		/*
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val date = dateFormat.parse(str)
        val format = SimpleDateFormat("h:mm a")
        return format.format(date!!)
		 */
		return "NA"
    }

    fun formattedTime(): String {
        return format(time)
    }

    fun formattedSunriseTime(): String {
		return format(sun_rise)
    }

    fun formattedSunsetTime(): String {
		return format(sun_set)
    }
}