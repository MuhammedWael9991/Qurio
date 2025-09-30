package com.qurio.ui.components

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class DurationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.parseColor("#DC6A43")
    }

    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 2f
        color = Color.BLACK
    }

    private val path = Path()
    private val strokePath = Path()

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

        strokePath.reset()
        strokePath.moveTo(306.739f * scaleX, 0.5f * scaleY)
        strokePath.lineTo(309.201f * scaleX, 2.635f * scaleY)
        strokePath.lineTo(291.835f * scaleX, 15.5f * scaleY)
        strokePath.lineTo(18.306f * scaleX, 15.5f * scaleY)
        strokePath.lineTo(0.77f * scaleX, 2.937f * scaleY)
        strokePath.lineTo(3.207f * scaleX, 0.5f * scaleY)
        strokePath.lineTo(306.739f * scaleX, 0.5f * scaleY)
        canvas.drawPath(strokePath, strokePaint)
    }
}

