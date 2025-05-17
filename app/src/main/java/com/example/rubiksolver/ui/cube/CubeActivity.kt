package com.example.rubiksolver.ui.cube

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.chaquo.python.Python
import com.chaquo.python.android.AndroidPlatform
import com.example.rubiksolver.R
import com.example.rubiksolver.extensions.colorNameToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream


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


        lifecycleScope.launch {
            try {
                val result = withContext(Dispatchers.IO) {
                    if (!Python.isStarted()) {
                        Python.start(AndroidPlatform(this@CubeActivity))
                    }
                    val python = Python.getInstance()
                    val module = python.getModule("cube_solver")
                    module.callAttr("solve_cube", "debug").toString()
                }
            } catch (e: Exception) {
                Log.e("MyLog", "Ошибка: ${e.message} ${e.stackTraceToString()}")
            }
        }


        val cubeState = cubeController.getCubeState()
        Log.d("My log", cubeState.toString())

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