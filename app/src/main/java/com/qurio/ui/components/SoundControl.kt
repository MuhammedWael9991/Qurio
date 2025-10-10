package com.qurio.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.widget.FrameLayout
import com.qurio.R
import com.qurio.databinding.VoiceBarBinding


class SoundControl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding = VoiceBarBinding.inflate(LayoutInflater.from(context), this, true)

    var soundLevel: Float = 0.5f
        set(value) {
            field = value.coerceIn(0f, 1f)
            updateIndicatorPosition()
        }

    var onSoundLevelChanged: ((Float) -> Unit)? = null

    init {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.SoundControlView)
        val title = ta.getString(R.styleable.SoundControlView_soundTitle) ?: ""
        val iconRes = ta.getResourceId(R.styleable.SoundControlView_soundIcon, R.drawable.ic_voice)
        soundLevel = ta.getFloat(R.styleable.SoundControlView_soundLevel, 0.5f)
        ta.recycle()

        binding.title.text = title
        binding.icon.setImageResource(iconRes)
        updateIndicatorPosition()

        setupDrag()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setupDrag() {
        binding.root.post {
            val barWidth = binding.soundBar.width.toFloat()
            val indicator = binding.soundIndicator

            indicator.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                        val parentStart = binding.soundBar.left.toFloat()
                        val parentEnd = binding.soundBar.right.toFloat()
                        val x = event.rawX - binding.root.left

                        soundLevel = ((x - parentStart) / (parentEnd - parentStart)).coerceIn(0f, 1f)
                        updateIndicatorPosition()
                        onSoundLevelChanged?.invoke(soundLevel)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun updateIndicatorPosition() {
        binding.root.post {
            val barStart = binding.soundBar.left
            val barWidth = binding.soundBar.width
            val indicator = binding.soundIndicator

            val x = barStart + barWidth * soundLevel - indicator.width / 2
            indicator.x = x.coerceIn(
                barStart.toFloat(),
                (barStart + barWidth - indicator.width).toFloat()
            )
        }
    }
}
