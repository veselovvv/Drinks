package com.veselovvv.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.veselovvv.drinks.databinding.FragmentCategoriesBinding
import com.veselovvv.drinks.presentation.core.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoriesBinding.inflate(inflater, container, false)
}