package com.sundeep1501.weatherforecast.backend.data

data class MWSource(
    val title: String,
    val slug: String,
    val url: String,
    val crawl_rate: Int
)