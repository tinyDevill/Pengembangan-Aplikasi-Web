package com.example.tugaspertama

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform