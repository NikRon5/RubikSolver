package com.example.rubiksolver.domain.models

import android.view.View

data class CubeCell (
    val face: CubeFace,
    val row: Int,
    val column: Int,
    val view: View,
    var color: Int
)