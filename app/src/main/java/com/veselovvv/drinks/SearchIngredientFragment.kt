package com.veselovvv.drinks

import android.view.LayoutInflater
import android.view.ViewGroup
import com.veselovvv.drinks.databinding.FragmentSearchIngredientBinding
import com.veselovvv.drinks.presentation.core.BaseFragment

class SearchIngredientFragment : BaseFragment<FragmentSearchIngredientBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentSearchIngredientBinding.inflate(inflater, container, false)
}