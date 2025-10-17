//package com.qurio.ui.components
//
//import android.content.Context
//import android.util.AttributeSet
//import android.view.LayoutInflater
//import android.widget.FrameLayout
//import com.qurio.R
//import com.qurio.databinding.LayoutCharacterButtonBinding
//import com.qurio.ui.components.QurioButtons.ButtonType
//
//class CharacterButtons @JvmOverloads constructor(
//    context: Context,
//    attrs: AttributeSet? = null
//) : FrameLayout(context, attrs) {
//
//    private val binding: LayoutCharacterButtonBinding =
//        LayoutCharacterButtonBinding.inflate(LayoutInflater.from(context), this, true)
//
//    var onClickConfirm: (() -> Unit)? = null
//    var onClickCancel: (() -> Unit)? = null
//    var onClickOk: (() -> Unit)? = null
//    var onClickBuy: (() -> Unit)? = null
//    var onClickBuyCharacter: (() -> Unit)? = null
//
//    init {
//        setupListeners()
//        context.theme.obtainStyledAttributes(attrs, R.styleable.CharacterButtons, 0, 0).apply {
//            try {
//                val typeIndex = getInt(R.styleable.CharacterButtons_buttonsType, 0)
//                showButtons(ButtonsType.fromInt(typeIndex))
//            } finally {
//                recycle()
//            }
//        }
//    }
//
//    fun showButtons(type: ButtonsType){
//        when(type){
//            ButtonsType.CONFIRM_CANCEL -> {
//                binding.confirmLayout.visibility = VISIBLE
//                binding.okLayout.visibility = GONE
//                binding.buyOkLayout.visibility = GONE
//                binding.buyCancelLayout.visibility = GONE
//            }
//            ButtonsType.OK -> {
//                binding.confirmLayout.visibility = GONE
//                binding.okLayout.visibility = VISIBLE
//                binding.buyOkLayout.visibility = GONE
//                binding.buyCancelLayout.visibility = GONE
//            }
//            ButtonsType.BUY_OK -> {
//                binding.confirmLayout.visibility = GONE
//                binding.okLayout.visibility = GONE
//                binding.buyOkLayout.visibility = VISIBLE
//                binding.buyCancelLayout.visibility = GONE
//            }
//            ButtonsType.BUY_CANCEL -> {
//                binding.confirmLayout.visibility = GONE
//                binding.okLayout.visibility = GONE
//                binding.buyOkLayout.visibility = GONE
//                binding.buyCancelLayout.visibility = VISIBLE
//            }
//            else -> {
//                binding.confirmLayout.visibility = GONE
//                binding.okLayout.visibility = GONE
//                binding.buyOkLayout.visibility = GONE
//                binding.buyCancelLayout.visibility = GONE
//            }
//        }
//    }
//
//    private fun setupListeners() {
//        binding.confirmButton.setOnClickListener { onClickConfirm?.invoke() }
//        binding.cancelButton.setOnClickListener { onClickCancel?.invoke() }
//        binding.okButton.setOnClickListener { onClickOk?.invoke() }
//        binding.buyButton.setOnClickListener { onClickBuy?.invoke() }
//        binding.okBuyButton.setOnClickListener { onClickOk?.invoke() }
//        binding.buyCancelButton.setOnClickListener { onClickBuyCharacter?.invoke() }
//        binding.buyCharacterCancelButton.setOnClickListener { onClickCancel?.invoke() }
//    }
//
//    enum class ButtonsType(val value: Int){
//        CONFIRM_CANCEL(0),
//        OK(1),
//        BUY_OK(2),
//        BUY_CANCEL(3);
//
//        companion object {
//            fun fromInt(value: Int) = ButtonsType.entries.first { it.value == value }
//        }
//    }
//
//}