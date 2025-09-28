package com.qurio.utilities

import androidx.databinding.BindingAdapter
import com.qurio.ui.components.QuirioButtons

// QurioButtons
@BindingAdapter("onButtonClick")
fun QuirioButtons.setOnButtonClick(action: (() -> Unit)?) {
    if (action != null) {
        this.setOnClickListener { action() }
    }
}
@BindingAdapter("buttonType")
fun QuirioButtons.setButtonType(typeValue: Int) {
    this.showButton(QuirioButtons.ButtonType.fromInt(typeValue))
}