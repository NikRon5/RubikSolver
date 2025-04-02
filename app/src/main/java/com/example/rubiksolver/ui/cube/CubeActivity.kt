package com.example.rubiksolver.ui.cube

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children
import com.example.rubiksolver.R
import com.example.rubiksolver.domain.models.CubeColors
import com.example.rubiksolver.domain.models.CubeFace
import com.example.rubiksolver.extensions.scaleDown
import com.example.rubiksolver.extensions.scaleToOriginal
import com.example.rubiksolver.extensions.setBackgroundColorAnimated

class CubeActivity : AppCompatActivity() {
    private var selectedCell: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cube)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.cube)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cellGridLayout : ConstraintLayout = findViewById(R.id.full_grid)

        setupCellsListener(cellGridLayout)
    }

    private fun setupCellsListener(fullCubeGrid: ConstraintLayout) {
        for (cubeFace in fullCubeGrid.children) {

            val cubeFaceGrid = cubeFace as? GridLayout ?: continue
            val cellColor = getFaceColor(cubeFaceGrid)
            for (i in 0 until  cubeFaceGrid.childCount) {
                val cell = cubeFaceGrid.getChildAt(i)
                initCellColor(cell, cellColor)
                cell.setOnClickListener { view ->
                    handleCellSelection(view)
                }
            }
        }
    }

    private fun getFaceColor(cubeFaceGrid: GridLayout): Int {
        val faceId = cubeFaceGrid.resources.getResourceEntryName(cubeFaceGrid.id)
        val faceName = faceId
            .substringBefore("_face")
            .uppercase()

        return CubeFace.entries
            .firstOrNull { it.name == faceName }
            ?.let { CubeColors.getFaceColor(it) }
            ?: throw IllegalStateException("Invalid face ID format. Expected '*_face', got: $faceId")
    }

    private fun initCellColor(cell: View, color: Int) {
        cell.setBackgroundColorAnimated(color)
    }

    private fun handleCellSelection(cell: View) {
        selectedCell?.scaleToOriginal()
        selectedCell = cell
        cell.scaleDown()
    }
}