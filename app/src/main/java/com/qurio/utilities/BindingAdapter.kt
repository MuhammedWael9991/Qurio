package com.qurio.utilities

import androidx.databinding.BindingAdapter
import com.qurio.ui.components.CharacterButtons
import com.qurio.ui.components.QurioButtons

// QurioButtons
@BindingAdapter("onButtonClick")
fun QurioButtons.setOnButtonClick(action: (() -> Unit)?) {
    if (action != null) {
        this.setOnClickListener { action() }
    }
}

// Characters Buttons
@BindingAdapter("buttonsType")
fun CharacterButtons.setCharactersButtonType(typeValue: Int) {
    this.showButtons(CharacterButtons.ButtonsType.fromInt(typeValue))
}

@BindingAdapter("buttonType")
fun QurioButtons.setButtonType(typeValue: Int) {
    this.showButton(QurioButtons.ButtonType.fromInt(typeValue))
}