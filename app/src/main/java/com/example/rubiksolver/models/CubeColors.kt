package com.example.rubiksolver.models

import android.graphics.Color

object CubeColors {
    val centerColors = mapOf(
        Face.FRONT to Color.RED,
        Face.UP to Color.WHITE,
        Face.LEFT to Color.GREEN,
        Face.RIGHT to Color.BLUE,
        Face.BACK to Color.parseColor("#FFA500"),
        Face.DOWN to Color.YELLOW
    )

    enum class Face {
        FRONT, UP, LEFT, RIGHT, BACK, DOWN
    }
}