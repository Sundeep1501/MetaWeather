package com.sundeep1501.weatherforecast.backend.data

import com.sundeep1501.weatherforecast.backend.MetaWeatherService
import java.text.SimpleDateFormat
import java.util.*

data class MWWeather(
    val id: Long,
    val weather_state_name: String,
    val weather_state_abbr: String,
    val wind_direction_compass: String,
    val created: String,
    val applicable_date: String,
    val min_temp: Double,
    val max_temp: Double,
    val the_temp: Double,
    val wind_speed: Double,
    val wind_direction: Double,
    val air_pressure: Double,
    val humidity: Int,
    val visibility: Double,
    val predictability: Int
) {
    fun getImageUrl(): String {
        return MetaWeatherService.getImageUrl(weather_state_abbr)
    }

    private fun formattedApplicableDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateFormat.parse(applicable_date)
        val format = SimpleDateFormat("EEE d", Locale.getDefault())
        return format.format(date!!)
    }

    fun formattedApplicableDate(index: Int): String {
        return when (index) {
            0 -> {
                "Today"
            }
            1 -> {
                "Tomorrow"
            }
            else -> formattedApplicableDate()
        }
    }
}