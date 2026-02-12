package com.example.tugaspertama

class JVMPlatform: Platform {
    override val name: String = "Desktop JVM"
}

actual fun getPlatform(): Platform = JVMPlatform()