package com.example.rubiksolver.ui.cube

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.rubiksolver.R
import com.example.rubiksolver.extensions.colorNameToInt


class CubeActivity : AppCompatActivity() {
    private lateinit var cubeController: CubeController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cube)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cube_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cubeContainer = findViewById<ConstraintLayout>(R.id.include_cube_container)

        cubeController = CubeController(
            rootView = cubeContainer,
            vibrationEnabled = true
        )

        cubeController.setupCube()

        cubeContainer.setOnClickListener {
            cubeController.clearSelection()
        }

        setupColorPalette()
    }

    private fun setupColorPalette() {
        val colorViews = listOf(
            R.id.palette_color_white,
            R.id.palette_color_blue,
            R.id.palette_color_green,
            R.id.palette_color_red,
            R.id.palette_color_orange,
            R.id.palette_color_yellow
        )

        colorViews.forEach { colorViewId ->
            findViewById<View>(colorViewId).setOnClickListener { view ->
                val color = view.tag.toString().colorNameToInt()
                cubeController.changeSelectedCellColor(color)
            }
        }
    }
}