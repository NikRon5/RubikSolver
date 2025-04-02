package com.example.rubiksolver.extensions

import android.animation.ValueAnimator
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.core.animation.doOnEnd
import com.example.rubiksolver.R

fun View.scaleDown(duration: Long = 200L) {
    this.animate()
        .scaleX(0.9f)
        .scaleY(0.9f)
        .setDuration(duration)
        .setInterpolator(AccelerateDecelerateInterpolator())
        .start()
}

fun View.scaleToOriginal(duration: Long = 200L) {
    animate()
        .scaleX(1f)
        .scaleY(1f)
        .alpha(1f)
        .setDuration(duration)
        .start()
}

fun View.setBackgroundColorAnimated(
    targetColor: Int,
    durationMs: Long = 200L
) {
    val background = background.mutate()

    if (background !is GradientDrawable) return

    val initialColor = (background.color?.defaultColor ?: Color.WHITE)

    // Cancel previous animator
    (getTag(R.id.color_animator_tag) as? ValueAnimator)?.let { oldAnimator ->
        oldAnimator.removeAllUpdateListeners()
        oldAnimator.cancel()
    }

    ValueAnimator.ofArgb(
        initialColor,
        targetColor
    ).apply {
        this.duration = durationMs
        interpolator = AccelerateDecelerateInterpolator()

        addUpdateListener { animator ->
            background.setColor(animator.animatedValue as Int)
        }

        doOnEnd {
            setTag(R.id.color_animator_tag, null)
        }

        // Saving and start
        setTag(R.id.color_animator_tag, this)
        start()
    }
}