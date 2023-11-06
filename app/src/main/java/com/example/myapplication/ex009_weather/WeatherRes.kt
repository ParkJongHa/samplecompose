package com.example.myapplication.ex009_weather

data class WeatherResponse(
    val response: Response,
)

data class Header(
    val resultCode: String, // "00",
    val resultMsg: String, // "NORMAL_SERVICE"
)

data class Response(
    val header: Header,
    val body: Body,
)

data class Body(
    val items: Items,
    val pageNo: Int,
    val numbOfRows: Int,
    val totalCount: Int,
) {
    fun toWeather(): Weather {
        var baseDate = ""
        var baseTime = ""
        var nx = ""
        var ny = ""
        var pty = ""
        var reh = ""
        var rn1 = ""
        var t1h = ""
        var uuu = ""
        var vec = ""
        var vvv = ""
        var wsd = ""

        items.item.forEach {
            if (baseDate.isBlank()) {
                baseDate = it.baseDate
                baseTime = it.baseTime
                nx = it.nx
                ny = it.ny
            }
            when (it.category) {
                "PTY" -> { pty = it.obsrValue } // 강수형태 / 코드값 / (초단기) 없음(0), 비(1), 비/눈(2), 눈(3), 빗방울(5), 빗방울눈날림(6), 눈날림(7)
                "REH" -> { reh = it.obsrValue } // 습도 / %
                "RN1" -> { rn1 = it.obsrValue }  // 1시간 강수량 / mm
                "T1H" -> { t1h = it.obsrValue }  // 기온 / ℃
                "UUU" -> { uuu = it.obsrValue }  // 동서바람성분 / m/s
                "VEC" -> { vec = it.obsrValue }  // 풍향 / deg
                "VVV" -> { vvv = it.obsrValue }  // 남북바람성분 / m/s
                "WSD" -> { wsd = it.obsrValue }  // 풍속 / m/s
            }
        }

        return Weather(
            baseDate,
            baseTime,
            nx,
            ny,
            pty,
            reh,
            rn1,
            t1h,
            uuu,
            vec,
            vvv,
            wsd
        )
    }
}

data class Items(
    val item: List<WeatherItem>,
)

data class WeatherItem(
    val baseDate: String, // 20231106</baseDate>
    val baseTime: String, // 1600</baseTime>
    val category: String, // PTY</category>
    val nx: String, // 55</nx>
    val ny: String, // 127</ny>
    val obsrValue: String, // 0</obsrValue>
)
