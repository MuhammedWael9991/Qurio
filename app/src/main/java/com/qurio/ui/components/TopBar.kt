package com.qurio.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.qurio.R
import com.qurio.databinding.TopBarBinding

class TopBar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private val binding: TopBarBinding =
        TopBarBinding.inflate(LayoutInflater.from(context), this, true)

    var onBackClick: (() -> Unit)? = null
    var onSettingsClick: (() -> Unit)? = null
    var onClickAvatar: (() -> Unit)? = null

    enum class Type(val value: Int) {
        PRIMARY(0),
        SECONDARY(1);

        companion object {
            fun fromValue(value: Int): Type = entries.firstOrNull { it.value == value } ?: PRIMARY
        }
    }

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBarView)
        val typeValue = typedArray.getInt(R.styleable.TopBarView_topBarType, 0)
        val type = Type.fromValue(typeValue)
        val title = typedArray.getString(R.styleable.TopBarView_topBarTitle)
        typedArray.recycle()

        when (type) {
            Type.PRIMARY -> {
                binding.topBarPrimary.visibility = VISIBLE
                binding.topBarSecondary.visibility = GONE
            }
            Type.SECONDARY -> {
                binding.topBarPrimary.visibility = GONE
                binding.topBarSecondary.visibility = VISIBLE
                binding.title.text = title ?: ""
            }
        }

        setupListeners()
    }

    private fun setupListeners() {
        binding.settingButton.setOnClickListener { onSettingsClick?.invoke() }
        binding.icBack.setOnClickListener { onBackClick?.invoke() }
        binding.avatarImage.setOnClickListener { onClickAvatar?.invoke() }
    }
}
