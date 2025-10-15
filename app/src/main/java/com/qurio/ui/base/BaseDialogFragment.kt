package com.qurio.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding

abstract class BaseDialogFragment<VB : ViewBinding>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
) : DialogFragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    open val dialogWidthPercentage: Double = 0.95
    open val dialogHeight: Int = WindowManager.LayoutParams.WRAP_CONTENT
    open val isBackgroundTransparent: Boolean = true
    open val gravity: Int = Gravity.CENTER

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                (resources.displayMetrics.widthPixels * dialogWidthPercentage).toInt(),
                dialogHeight
            )
            if (isBackgroundTransparent)
                setBackgroundDrawableResource(android.R.color.transparent)
            setGravity(gravity)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
