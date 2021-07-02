package com.flicker.sampleapp.presentation

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BaseApplication : Application() {
    val isDark = mutableStateOf(false)
    fun toggleTheme() {
        isDark.value = !isDark.value
    }
}