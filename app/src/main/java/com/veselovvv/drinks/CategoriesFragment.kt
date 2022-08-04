package com.veselovvv.drinks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.veselovvv.drinks.databinding.FragmentCategoriesBinding
import com.veselovvv.drinks.presentation.core.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoriesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesCategoriesCardView.setOnClickListener {
            navigateToFragmentWithCategory("c")
        }

        binding.categoriesGlassCardView.setOnClickListener {
            navigateToFragmentWithCategory("g")
        }

        binding.categoriesIngredientsCardView.setOnClickListener {
            navigateToFragmentWithCategory("i")
        }

        binding.categoriesAlcoholCardView.setOnClickListener {
            navigateToFragmentWithCategory("a")
        }
    }

    private fun navigateToFragmentWithCategory(categoryKey: String) {
        val bundle = Bundle()
        bundle.putString("category", categoryKey)
        requireActivity().findNavController(R.id.fragment_container_view)
            .navigate(R.id.subcategoriesFragment, bundle)
    }
}