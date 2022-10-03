package com.veselovvv.drinks.presentation.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.viewbinding.ViewBinding
import com.veselovvv.drinks.R

abstract class BaseFragment<B : ViewBinding> : Fragment() {
    private var _binding: B? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = setupViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?): B

    protected fun navigateWithArguments(@IdRes destinationFragment: Int, bundle: Bundle) =
        requireActivity().findNavController(R.id.fragment_container_view)
            .navigate(destinationFragment, bundle)

    protected fun getStringArgument(key: String) =
        requireActivity().findNavController(R.id.fragment_container_view)
            .backQueue.last().arguments?.getString(key) ?: ""
}