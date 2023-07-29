package com.example.hackathonwinnersapp.presentation.ui.dialog

import android.app.AlertDialog
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.hackathonwinnersapp.domain.models.executor.ExecutorDomainModel
import com.example.hackatonwinnersapp.databinding.DialogAddExecutorLayoutBinding
import com.example.hackatonwinnersapp.databinding.DialogOrderLayoutBinding

fun Fragment.initDialogToAddOrder(
    title: String,
    firstTvHint: String,
    secondTvHint: String,
    positiveAction: (String, String) -> Unit
) {

    val binding = DialogOrderLayoutBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(context)
        .setCancelable(false)
        .setView(binding.root)
        .create()

    with(binding) {
        tvTitle.text = title
        orderTitleEt.hint = firstTvHint
        orderCostEt.hint = secondTvHint

        with(builder) {
            orderCancelButton.setOnClickListener {
                dismiss()
            }
            orderAddButton.setOnClickListener {
                if (orderTitleEt.text.toString().isNotBlank()
                    && orderCostEt.text.toString().isNotBlank()
                ) {
                    positiveAction.invoke(orderTitleEt.text.toString(), orderCostEt.text.toString())
                    dismiss()
                }
            }
//            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }
}

fun Fragment.initDialogToAddExecutor(
    executorsList: List<ExecutorDomainModel>,
    positiveAction: (String) -> Unit
) {

    val binding = DialogAddExecutorLayoutBinding.inflate(layoutInflater)
    val builder = AlertDialog.Builder(context)
        .setCancelable(false)
        .setView(binding.root)
        .create()

    with(binding) {
        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            executorsList
        )
        binding.spinnerExecutor.adapter = spinnerAdapter
        with(builder) {
            executorCancelButton.setOnClickListener { dismiss() }
            executorAddButton.setOnClickListener {
                if (spinnerExecutor.selectedItem.toString().isNotBlank()) {
                    positiveAction.invoke(spinnerExecutor.selectedItem.toString())
                    dismiss()
                }
            }

//            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            show()
        }
    }
}