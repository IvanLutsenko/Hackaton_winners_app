package com.example.hackathonwinnersapp.presentation.ui.executor.tab

import androidx.fragment.app.Fragment

class ExecutorTabFragment : Fragment() {

    private val tabType: String by lazy {
        requireArguments().getString(EXECUTOR_TAB_TYPE_TAG, ExecutorTabType.ORDER.name)
    }

    companion object {

        const val EXECUTOR_TAB_TYPE_TAG = "EXECUTOR_TAB_TYPE_TAG"
    }
}
