package com.example.myapplication.ex009_weather

data class WeatherReq(
    val serviceKey: String = "DpwkGNt22vwsg9XosqUJYZMQHw5HI30TPafQbbzpvyuU%2BXifQw092irBtdNL65znnJNonUb7AbrW7wrOSSqrKg%3D%3D",
    val numOfRows: Int = 10,
    val pageNo: Int = 1,

    val baseDate: String,
    val baseTime: String,
    val nx: Int,
    val ny: Int,
) {
    fun getQueryString(): String {
        return "" +
            "?serviceKey=" + serviceKey +
            "&numOfRows=" + numOfRows +
            "&pageNo=" + pageNo +
            "&base_date=" + baseDate +
            "&base_time=" + baseTime +
            "&nx=" + nx +
            "&ny=" + ny +

            "&dataType=JSON"
    }
}
