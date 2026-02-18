package com.example.tugaskedua

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import data.NewsRepository
import presentation.NewsViewModel
import ui.NewsScreen

@Composable
fun App() {
    MaterialTheme {
        val vm = remember { NewsViewModel(NewsRepository()) }

        LaunchedEffect(Unit) {
            vm.startStreaming()
        }

        NewsScreen(vm)
    }
}
