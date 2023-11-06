package com.example.myapplication.ex008_ktor

import androidx.lifecycle.ViewModel
import com.example.myapplication.ex009_weather.Weather
import com.example.myapplication.ex009_weather.WeatherReq
import com.example.myapplication.ex009_weather.WeatherRes
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayInputStream
import java.io.InputStreamReader


class KtorViewModel: ViewModel() {
    fun req() {
        CoroutineScope(Dispatchers.IO).launch {

            // param set
            val weatherReq = WeatherReq(
                baseDate = "20231106",
                baseTime = "1800",
                nx = 55,
                ny = 127
            )
            val api = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"
            val url = "$api${weatherReq.getQueryString()}"

            // request
            val httpClient = HttpClient(CIO)
            val httpResponse: HttpResponse = httpClient.get(url)
            val inputStreamReader = InputStreamReader(ByteArrayInputStream(httpResponse.readBytes()))

            // data transform
            val weatherRes: WeatherRes = Gson().fromJson(inputStreamReader, WeatherRes::class.java)
            val weather: Weather = weatherRes.response.body.toWeather()

            // release
            inputStreamReader.close()
            httpClient.close()

            // output
            println("------------------------------------------------------------")
            println(weather)
            println("------------------------------------------------------------")
        }
    }
}