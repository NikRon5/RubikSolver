package com.example.rubiksolver

import android.animation.ValueAnimator
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.GridLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.children

class CubeActivity : AppCompatActivity() {

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
        for (view in fullCubeGrid.children) {
            val cubeFace = view as? GridLayout ?: continue

            for (i in 0 until  cubeFace.childCount) {
                val cell = cubeFace.getChildAt(i)
                cell.setOnClickListener { view ->
                    handleCellSelection(cell)
                }
            }
        }
    }

    private fun handleCellSelection(selectedCell: View) {

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
}