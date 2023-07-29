package com.example.hackathonwinnersapp.presentation.ui.dialog

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.*
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.FrameLayout
import androidx.viewbinding.ViewBinding
import com.example.hackatonwinnersapp.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseDialog<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB,
    private val fullHeight: Boolean = true,
    private val canDrag: Boolean = true,
    private val canCancel: Boolean = true
) : BottomSheetDialogFragment() {

    private var _binding: VB? = null
    val binding get() = requireNotNull(_binding)

    private lateinit var bottomSheetDialog: BottomSheetDialog

    private val behavior by lazy {
        bottomSheetDialog.behavior
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.AppBottomSheetDialogTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = bindingInflater.invoke(inflater)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onCreateDialog(savedInstanceState: Bundle?): BottomSheetDialog {
        bottomSheetDialog = super.onCreateDialog(savedInstanceState) as BottomSheetDialog
        behavior.disableShapeAnimations()
        return bottomSheetDialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (dialog is BottomSheetDialog) {
            overrideCancelable(canCancel)
            setDraggable(canDrag)
            view.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
                /**
                 * В горизонтальной ориентации диалог отображался неправильно
                 */
                override fun onGlobalLayout() {
                    view.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    if (fullHeight) {
                        setupFullHeight()
                    } else {
                        behavior.skipCollapsed = true
                    }
                    behavior.state = BottomSheetBehavior.STATE_EXPANDED
                }
            })
        }
        setUi()
    }

    fun setDraggable(canDrag: Boolean) {
        if (!canDrag) {
            val bottomSheetCallback = object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_DRAGGING) {
                        behavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) = Unit
            }
            with(behavior) {
                removeBottomSheetCallback(bottomSheetCallback)
                addBottomSheetCallback(bottomSheetCallback)
            }
        }
    }

    fun overrideCancelable(canCancel: Boolean) {
        bottomSheetDialog.setCancelable(canCancel)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected open fun setUi() = Unit

    private fun setupFullHeight() {
        val bottomSheet =
            bottomSheetDialog.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
        val layoutParams = bottomSheet?.layoutParams
        layoutParams?.let {
            it.height = getWindowHeight()
            bottomSheet.layoutParams = it
        }
    }

    private fun getWindowHeight() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val windowMetrics = requireActivity().windowManager.currentWindowMetrics
        val insets = windowMetrics.windowInsets
            .getInsetsIgnoringVisibility(WindowInsets.Type.systemBars())
        windowMetrics.bounds.height() - insets.top - insets.bottom
    } else {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        displayMetrics.heightPixels
    }
}