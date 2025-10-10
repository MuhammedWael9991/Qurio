package com.qurio.ui.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.qurio.R

class SoundBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = ContextCompat.getColor(context, R.color.Primary_variant)
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        color = Color.BLACK
    }

    private val path = Path()

    private val originalW = 310f
    private val originalH = 16f

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val scaleX = width / originalW
        val scaleY = height / originalH

        path.reset()
        path.moveTo(0f * scaleX, 3f * scaleY)
        path.lineTo(3f * scaleX, 0f * scaleY)
        path.lineTo(306.926f * scaleX, 0f * scaleY)
        path.lineTo(310f * scaleX, 2.667f * scaleY)
        path.lineTo(292f * scaleX, 16f * scaleY)
        path.lineTo(18.1454f * scaleX, 16f * scaleY)
        path.close()
        canvas.drawPath(path, fillPaint)
    }
}

