package com.example.tugaskedua

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "TugasKedua",
    ) {
        App()
    }
}