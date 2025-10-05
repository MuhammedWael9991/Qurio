package com.qurio.ui.components

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.animation.doOnEnd
import com.qurio.databinding.StatisticsBinding

class Statistics @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: StatisticsBinding =
        StatisticsBinding.inflate(LayoutInflater.from(context), this, true)

    var onAwardsClick: (() -> Unit)? = null

    init {
        binding.AwardsValue.setOnClickListener { onAwardsClick?.invoke() }
    }

    fun setData(lives: Int, points: Int, awards: Int, animate: Boolean = true) {
        if (animate) {
            animateNumber(binding.lives, lives)
            animateNumber(binding.points, points)
            animateNumber(binding.awards, awards)
        } else {
            binding.lives.text = lives.toString()
            binding.points.text = String.format("%,d", points)
            binding.awards.text = awards.toString()
            binding.eliteBadge.visibility = if (points >= 10000) VISIBLE else GONE
        }
    }

    private fun animateNumber(textView: android.widget.TextView, targetValue: Int) {
        val animator = ValueAnimator.ofInt(0, targetValue)
        animator.duration = 1000
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue as Int
            if (textView.id == binding.points.id)
                textView.text = String.format("%,d", value)
            else
                textView.text = value.toString()
        }
        animator.doOnEnd {}
        animator.start()
    }
}

