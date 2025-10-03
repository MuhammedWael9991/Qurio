package com.qurio.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.qurio.R

class GradientTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    @SuppressLint("DrawAllocation")
    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        val height = height.toFloat()
        val shader = LinearGradient(
            0f, 0f, 0f, height,
            intArrayOf(
                ContextCompat.getColor(context, R.color.white),
                ContextCompat.getColor(context, R.color.Primary)
            ),
            null,
            Shader.TileMode.CLAMP
        )
        paint.shader = shader
    }
}
