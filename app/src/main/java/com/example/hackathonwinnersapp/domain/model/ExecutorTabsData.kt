package com.example.hackathonwinnersapp.domain.model

import com.example.hackathonwinnersapp.presentation.ui.executor.tab.ExecutorTabType

data class ExecutorTabsData(
    val title: String,
    val type: ExecutorTabType
)