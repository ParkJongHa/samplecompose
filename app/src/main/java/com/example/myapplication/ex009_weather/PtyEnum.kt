package com.example.myapplication.ex009_weather

// Precipitation / 강수
enum class PtyEnum (
    val id: Int,
    val description: String,
) {
    None(0, "없음"),
    Rain(1, "비"),
    RainAndSnow(2, "비/눈"),
    Snow(3, "눈"),
    Raindrop(5, "빗방울"),
    RaindropFlipping(6, "빗방울눈날림"),
    SnowFlipping(7, "비/눈"),
    ;

    fun get(id: Int): PtyEnum {
        return values().firstOrNull { it.id == id } ?: None
    }
}