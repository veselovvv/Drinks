package com.veselovvv.drinks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.veselovvv.drinks.core.Category
import com.veselovvv.drinks.databinding.FragmentCategoriesBinding
import com.veselovvv.drinks.presentation.core.BaseFragment

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCategoriesBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoriesCategoriesCardView.setOnClickListener {
            navigateToFragmentWithCategory(Category.CATEGORIES)
        }

        binding.categoriesGlassCardView.setOnClickListener {
            navigateToFragmentWithCategory(Category.GLASS)
        }

        binding.categoriesIngredientsCardView.setOnClickListener {
            navigateToFragmentWithCategory(Category.INGREDIENTS)
        }

        binding.categoriesAlcoholCardView.setOnClickListener {
            navigateToFragmentWithCategory(Category.ALCOHOLIC)
        }
    }

    private fun navigateToFragmentWithCategory(category: Category) {
        val bundle = Bundle()
        bundle.putSerializable(CATEGORY, category)
        navigateWithArguments(R.id.subcategoriesFragment, bundle)
    }

    companion object {
        private const val CATEGORY = "category"
    }
}