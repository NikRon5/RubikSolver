package com.example.rubiksolver.extensions

import android.graphics.Color
import androidx.annotation.ColorInt
import com.example.rubiksolver.domain.models.ColorConfig

@ColorInt
fun String.parseColorSafe(default: Int = Color.WHITE): Int {
    return try {
        Color.parseColor(this)
    } catch (e: IllegalArgumentException) {
        default
    }
}

@ColorInt
fun String.colorNameToInt(): Int {
    val hex = ColorConfig.colorMap[uppercase()]
    return hex?.parseColorSafe() ?: Color.WHITE
}