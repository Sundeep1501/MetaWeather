package com.sundeep1501.weatherforecast.backend.data

data class MWLocation(
    val title: String,
    val location_type: String,
    val woeid: Int,
    val latt_long: String
)