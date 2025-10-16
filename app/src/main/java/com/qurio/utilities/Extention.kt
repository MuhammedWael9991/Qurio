package com.qurio.utilities

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.view.View
import android.view.animation.OvershootInterpolator
import kotlinx.coroutines.*

fun View.popInAnimation(duration: Long = 500L) {
    visibility = View.VISIBLE
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0f, 1.3f, 1f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0f, 1.3f, 1f)
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 0f, 1f)

    ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY, alpha).apply {
        this.duration = duration
        interpolator = OvershootInterpolator()
        start()
    }
}

fun View.popOutAnimation(duration: Long = 500L) {
    val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.3f, 0f)
    val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.3f, 0f)
    val alpha = PropertyValuesHolder.ofFloat(View.ALPHA, 1f, 0f)

    ObjectAnimator.ofPropertyValuesHolder(this, scaleX, scaleY, alpha).apply {
        this.duration = duration
        interpolator = OvershootInterpolator()
        start()
    }

    postDelayed({ visibility = View.GONE }, duration)
}

fun playSequentialBounce(views: List<View>, delayBetween: Long = 150L) {
    CoroutineScope(Dispatchers.Main).launch {
        for (view in views) {
            view.popInAnimation()
            delay(delayBetween)
        }

        delay(800)

        for (view in views.reversed()) {
            view.popOutAnimation()
            delay(delayBetween)
        }
    }
}

