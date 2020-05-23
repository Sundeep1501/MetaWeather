package com.sundeep1501.weatherforecast.backend.data

data class MWLocationInfo (
	val consolidated_weather : List<MWWeather>,
	val time : String,
	val sun_rise : String,
	val sun_set : String,
	val timezone_name : String,
	val parent : MWParent,
	val sources : List<MWSource>,
	val title : String,
	val location_type : String,
	val woeid : Int,
	val latt_long : String,
	val timezone : String
)