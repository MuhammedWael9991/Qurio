package com.qurio.utilities

import androidx.databinding.BindingAdapter
import com.qurio.ui.components.QurioButtons

// QurioButtons
@BindingAdapter("onButtonClick")
fun QurioButtons.setOnButtonClick(action: (() -> Unit)?) {
    if (action != null) {
        this.setOnClickListener { action() }
    }
}
@BindingAdapter("buttonType")
fun QurioButtons.setButtonType(typeValue: Int) {
    this.showButton(QurioButtons.ButtonType.fromInt(typeValue))
}