package com.example.rubiksolver.models

import android.graphics.Color

object CubeColors {
    val centerColors = mapOf(
        Face.FRONT to Color.parseColor("#C41E3A"),
        Face.UP to Color.parseColor("#FFFFFF"),
        Face.LEFT to Color.parseColor("#009E60"),
        Face.RIGHT to Color.parseColor("#0051BA"),
        Face.BACK to Color.parseColor("#FFA500"),
        Face.DOWN to Color.parseColor("#FFD500")
    )

    enum class Face {
        FRONT, UP, LEFT, RIGHT, BACK, DOWN
    }
}