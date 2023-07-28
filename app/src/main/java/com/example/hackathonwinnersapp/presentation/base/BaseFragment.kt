package com.example.hackathonwinnersapp.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.hackathonwinnersapp.util.StringResource
import com.example.hackatonwinnersapp.databinding.FragmentBaseBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.awac.cBoRRates.util.getString

abstract class BaseFragment<T : BaseViewModel, V : ViewBinding>(
    private val inflater: (LayoutInflater, ViewGroup?, Boolean) -> V,
) : Fragment() {

    abstract val viewModel: T

    private var _binding: V? = null
    protected val binding by lazy { checkNotNull(_binding) }

    private var _baseBinding: FragmentBaseBinding? = null
    protected val baseBinding by lazy { checkNotNull(_baseBinding) }

    val navController by lazy(LazyThreadSafetyMode.NONE) {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _baseBinding = FragmentBaseBinding.inflate(inflater, container, false)
        _binding = inflater(inflater, baseBinding.content, true)

        viewModel.baseSideEffects
            .onEach(::handleBaseSideEffect)
            .launchIn(viewLifecycleOwner.lifecycleScope)

        return baseBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apply {
            isInProgress.onEach(::onProgress).launchIn(viewLifecycleOwner.lifecycleScope)
            toastText.onEach(::showToast).launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onDestroyView() {
        _binding = null
        _baseBinding = null
        super.onDestroyView()
    }

    open fun onProgress(inProgress: Boolean) {
        baseBinding.progressBar.isVisible = inProgress
    }

    private fun handleBaseSideEffect(sideEffect: BaseSideEffect) {
        when (sideEffect) {
            is BaseSideEffect.Toast -> showToast(sideEffect.message)
        }
    }

    private fun showToast(message: StringResource, length: Int = Toast.LENGTH_LONG) {
        val text = requireContext().getString(message)
        if (text?.isNotEmpty() == true)
            Toast.makeText(requireContext(), requireContext().getString(message), length).show()
    }
}
