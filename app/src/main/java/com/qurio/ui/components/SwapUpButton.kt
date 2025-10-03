package com.qurio.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import com.qurio.R
import com.qurio.databinding.SwapUpButtonBinding
import kotlin.math.max
import kotlin.math.min


class SwapUpButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var _binding: SwapUpButtonBinding? = null
    private val binding get() = _binding!!

    private var startY = 0f
    private var isDragging = false
    private var onSwapCompleteListener: (() -> Unit)? = null

    init {
        _binding = SwapUpButtonBinding.inflate(LayoutInflater.from(context), this)
        initTouch()
        startArrowAnimations()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initTouch() {
        binding.swapIndicator.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    startY = event.rawY
                    isDragging = true
                    true
                }

                MotionEvent.ACTION_MOVE -> {
                    if (isDragging) {
                        val dy = event.rawY - startY
                        val newTranslationY = v.translationY + dy

                        // limit the drag inside parent bounds
                        val minY = -binding.root.height.toFloat() + v.height
                        val maxY = 0f
                        v.translationY = min(maxY, max(minY, newTranslationY))

                        startY = event.rawY
                    }
                    true
                }

                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    isDragging = false
                    // check if reached top
                    if (v.translationY <= -binding.root.height / 2) {
                        // trigger callback
                        onSwapCompleteListener?.invoke()
                    }
                    // reset position (animate back)
                    v.animate().translationY(0f).setDuration(200).start()
                    true
                }

                else -> false
            }
        }
    }

    fun setOnSwapCompleteListener(listener: () -> Unit) {
        onSwapCompleteListener = listener
    }

    private fun startArrowAnimations() {
        val fadeAnim = AnimationUtils.loadAnimation(context, R.anim.fade_in_out)

        binding.firstArrow.startAnimation(fadeAnim)

        val fadeAnim2 = AnimationUtils.loadAnimation(context, R.anim.fade_in_out).apply {
            startOffset = 200
        }
        binding.secondArrow.startAnimation(fadeAnim2)

        val fadeAnim3 = AnimationUtils.loadAnimation(context, R.anim.fade_in_out).apply {
            startOffset = 400
        }
        binding.thirdArrow.startAnimation(fadeAnim3)
    }

}