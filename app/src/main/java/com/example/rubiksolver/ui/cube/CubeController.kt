package com.example.rubiksolver.ui.cube

import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.GridLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.example.rubiksolver.domain.models.CubeColors
import com.example.rubiksolver.domain.models.CubeFace
import com.example.rubiksolver.extensions.scaleDown
import com.example.rubiksolver.extensions.scaleToOriginal
import com.example.rubiksolver.extensions.setBackgroundColorAnimated

class CubeController(
    private val rootView: ConstraintLayout,
    private val vibrationEnabled: Boolean = true
) {

    private var selectedCell: View? = null

    fun setupCube() {
        require(rootView.childCount > 0) {
            "Root view must contain cube faces"
        }

        rootView.children.forEach { faceView ->
            val grid = faceView as? GridLayout ?: return@forEach
            val color = parseFaceColor(grid)
            initCells(grid, color)
        }
    }

    private fun parseFaceColor(faceGrid: GridLayout): Int {
        val faceName = faceGrid.resources.getResourceEntryName(faceGrid.id)
            .substringBefore("_face")
            .uppercase()

        return CubeFace.valueOf(faceName).let(CubeColors::getFaceColor)
    }

    private fun initCells(faceGrid: GridLayout, color: Int) {
        for (i in 0 until faceGrid.childCount) {
            faceGrid.getChildAt(i).apply {
                setBackgroundColorAnimated(color)
                setOnClickListener { view ->
                    handleCellClick(view)
                }
            }
        }
    }

    private fun handleCellClick(cell: View) {
        if (vibrationEnabled) {
            cell.performHapticFeedback(HapticFeedbackConstants.CONTEXT_CLICK)
        }

        clearSelection()
        selectedCell = cell
        addSelection()
    }

    private fun clearSelection() {
        selectedCell?.scaleToOriginal()
        selectedCell = null
    }

    private fun addSelection() {
        selectedCell?.scaleDown()
    }

    fun changeSelectedCellColor(color: Int) {
        selectedCell?.setBackgroundColorAnimated(color, 150)
    }
}
