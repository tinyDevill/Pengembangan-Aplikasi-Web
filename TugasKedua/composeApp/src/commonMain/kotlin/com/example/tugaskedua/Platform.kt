package com.example.tugaskedua

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform