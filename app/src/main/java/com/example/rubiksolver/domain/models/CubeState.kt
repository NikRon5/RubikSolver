package com.example.rubiksolver.domain.models

data class CubeState(
    val front: List<Int>,
    val up: List<Int>,
    val left: List<Int>,
    val right: List<Int>,
    val back: List<Int>,
    val down: List<Int>
)