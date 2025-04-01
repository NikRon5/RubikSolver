package com.example.rubiksolver.domain.models

enum class CubeFace {
    FRONT, UP, LEFT, RIGHT, BACK, DOWN;

    companion object {
        fun fromString(value: String): CubeFace? {
            return entries.firstOrNull { it.name.equals(value, ignoreCase = true) }
        }
    }
}