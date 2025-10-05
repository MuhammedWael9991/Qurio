package com.qurio.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.qurio.R

class QurioButtons @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle) {

    private val primaryBig: ImageView
    private val primarySmall: ImageView
    private val primaryDisabled: FrameLayout
    private val outlinedEnabled: FrameLayout
    private val outlinedDisabled: FrameLayout
    private val gameTextEnabled: TextView
    private val gameTextDisabled: TextView

    private var currentView: View? = null

    init {
        inflate(context, R.layout.qurio_buttons, this)

        primaryBig = findViewById(R.id.button_primary_big)
        primarySmall = findViewById(R.id.button_primary_small)
        primaryDisabled = findViewById(R.id.button_primary_disabled)
        outlinedEnabled = findViewById(R.id.button_outlined_enabled)
        outlinedDisabled = findViewById(R.id.button_outlined_disabled)
        gameTextEnabled = findViewById(R.id.button_text_enabled)
        gameTextDisabled = findViewById(R.id.button_text_disabled)

        context.theme.obtainStyledAttributes(attrs, R.styleable.QuiroButtons, 0, 0).apply {
            try {
                val typeIndex = getInt(R.styleable.QuiroButtons_buttonType, 0)
                showButton(ButtonType.fromInt(typeIndex))
            } finally {
                recycle()
            }
        }
    }

    fun showButton(type: ButtonType) {
        hideAll()
        currentView = when (type) {
            ButtonType.PRIMARY_BIG -> primaryBig
            ButtonType.PRIMARY_SMALL -> primarySmall
            ButtonType.PRIMARY_DISABLED -> primaryDisabled
            ButtonType.OUTLINED_ENABLED -> outlinedEnabled
            ButtonType.OUTLINED_DISABLED -> outlinedDisabled
            ButtonType.GAME_TEXT_ENABLED -> gameTextEnabled
            ButtonType.GAME_TEXT_DISABLED -> gameTextDisabled
        }
        currentView?.visibility = VISIBLE
    }

    override fun setOnClickListener(l: OnClickListener?) {
        currentView?.setOnClickListener(l)
    }

    private fun hideAll() {
        listOf(
            primaryBig, primarySmall, primaryDisabled,
            outlinedEnabled, outlinedDisabled,
            gameTextEnabled, gameTextDisabled
        ).forEach { it.visibility = GONE }
    }

    enum class ButtonType(val value: Int) {
        PRIMARY_BIG(0),
        PRIMARY_SMALL(1),
        PRIMARY_DISABLED(2),
        OUTLINED_ENABLED(3),
        OUTLINED_DISABLED(4),
        GAME_TEXT_ENABLED(5),
        GAME_TEXT_DISABLED(6);

        companion object {
            fun fromInt(value: Int) = ButtonType.entries.first { it.value == value }
        }
    }
}
