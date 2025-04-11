package com.example.rubiksolver.domain.models

import com.example.rubiksolver.extensions.parseColorSafe

object CubeColors {
    private val faceColors: Map<CubeFace, Int> = mapOf(
        CubeFace.FRONT to ColorConfig.CUBE_WHITE.parseColorSafe(),
        CubeFace.UP to ColorConfig.CUBE_BLUE.parseColorSafe(),
        CubeFace.LEFT to ColorConfig.CUBE_ORANGE.parseColorSafe(),
        CubeFace.RIGHT to ColorConfig.CUBE_RED.parseColorSafe(),
        CubeFace.BACK to ColorConfig.CUBE_YELLOW.parseColorSafe(),
        CubeFace.DOWN to ColorConfig.CUBE_GREEN.parseColorSafe()
    )

    fun getFaceColor(face: CubeFace): Int {
        return faceColors[face] ?: throw IllegalStateException("Color for face $face not found")
    }
}