package com.example.rubiksolver

import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children

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
        val face = cubeFaceGrid.resources.getResourceName(cubeFaceGrid.id)
            .substringAfter("id/")
            .substringBefore("_face")

        return CubeColors.centerColors[face]!!
    }

    private fun initCellColor(cell: View, color: Int) {
        changeCellColor(cell, color, 0)
    }

    private fun handleCellSelection(cell: View) {
        selectedCell?.scaleToOriginal()
        selectedCell = cell
        cell.scaleDown()
    }

    private fun changeCellColor(cell: View, targetColor: Int, duration: Long = 200L) {
        val cellGradientDrawable = cell.background.mutate() as GradientDrawable
        val startColor = cellGradientDrawable.color?.defaultColor ?: -1

        ValueAnimator.ofArgb(startColor, targetColor).apply {
            this.duration = duration
            addUpdateListener { animator ->
                cellGradientDrawable.setColor(animator.animatedValue as Int)
            }
            start()
        }
    }

    private fun View.scaleDown(duration: Long = 200L) {
        this.animate()
            .scaleX(0.9f)
            .scaleY(0.9f)
            .setDuration(duration)
            .setInterpolator(AccelerateDecelerateInterpolator())
            .start()
    }

    private fun View.scaleToOriginal(duration: Long = 200L) {
        animate()
            .scaleX(1f)
            .scaleY(1f)
            .alpha(1f)
            .setDuration(duration)
            .start()
    }
}