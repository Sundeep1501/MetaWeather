package com.sundeep1501.weatherforecast.backend.data

data class MWLocation(
    val title: String,
    val location_type: String,
    val woeid: Long,
    val latt_long: String
)