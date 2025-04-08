package com.example.rubiksolver.domain.models

import android.graphics.Color

object CubeColors {
    private const val CUBE_WHITE = "#FFFFFF"
    private const val CUBE_BLUE = "#0051BA"
    private const val CUBE_ORANGE = "#FFA500"
    private const val CUBE_RED = "#C41E3A"
    private const val CUBE_YELLOW = "#FFD500"
    private const val CUBE_GREEN = "#009E60"

    private val faceColors: Map<CubeFace, Int> = mapOf(
        CubeFace.FRONT to parseColorSafe(CUBE_WHITE),
        CubeFace.UP to parseColorSafe(CUBE_BLUE),
        CubeFace.LEFT to parseColorSafe(CUBE_ORANGE),
        CubeFace.RIGHT to parseColorSafe(CUBE_RED),
        CubeFace.BACK to parseColorSafe(CUBE_YELLOW),
        CubeFace.DOWN to parseColorSafe(CUBE_GREEN)
    )

    fun getFaceColor(face: CubeFace): Int {
        return faceColors[face] ?: throw IllegalStateException("Color for face $face not found")
    }

    private fun parseColorSafe(hex: String): Int {
        return try {
            Color.parseColor(hex)
        } catch (e: IllegalArgumentException) {
            Color.WHITE
        }
    }
}