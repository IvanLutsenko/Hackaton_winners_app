package com.example.hackathonwinnersapp.presentation.ui.order_popup

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import com.example.hackatonwinnersapp.databinding.DialogOrderLayoutBinding

fun Fragment.initDialogToSetText(positiveAction: (String, String) -> Unit) {

    val binding = DialogOrderLayoutBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(context)
        .setCancelable(false)
        .setView(binding.root)
        .create()

    with(binding) {
        with(builder) {
            orderCancelButton.setOnClickListener {
                dismiss()
            }
            orderAddButton.setOnClickListener {
                if (orderTitleEt.text.toString().isNotBlank() && orderCostEt.text.toString()
                        .isNotBlank()
                ) {
                    positiveAction.invoke(orderTitleEt.text.toString(), orderCostEt.text.toString())
                    dismiss()
                }
            }

            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }
}