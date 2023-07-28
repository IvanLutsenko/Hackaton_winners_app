package com.example.hackathonwinnersapp.presentation.ui.executor.tab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathonwinnersapp.domain.model.ExecutorModel
import com.example.hackathonwinnersapp.presentation.ui.executor.recyclerview.ExecutorAdapter
import com.example.hackatonwinnersapp.databinding.FragmentExecutorTabBinding

class ExecutorTabFragment : Fragment() {

    private var _binding: FragmentExecutorTabBinding? = null
    val binding get() = requireNotNull(_binding)

    private val tabType: String by lazy {
        requireArguments().getString(EXECUTOR_TAB_TYPE_TAG, ExecutorTabType.ORDER.name)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExecutorTabBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvExecutor.layoutManager = LinearLayoutManager(binding.root.context)
        binding.rvExecutor.adapter = ExecutorAdapter(handleRecyclerViewDataByType())
    }

    private fun handleRecyclerViewDataByType(): List<ExecutorModel> = when (tabType) {
        ExecutorTabType.ORDER.name -> {
            listOf()
        }

        ExecutorTabType.PAYMENT.name -> {
            listOf()
        }

        ExecutorTabType.TAXES.name -> {
            listOf()
        }

        else -> listOf()
    }

    companion object {

        const val EXECUTOR_TAB_TYPE_TAG = "EXECUTOR_TAB_TYPE_TAG"
    }
}
