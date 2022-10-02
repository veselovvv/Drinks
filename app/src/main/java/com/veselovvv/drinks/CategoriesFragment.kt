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
            navigateToFragmentWithCategory(CATEGORY_CATEGORY_KEY)
        }

        binding.categoriesGlassCardView.setOnClickListener {
            navigateToFragmentWithCategory(GLASS_CATEGORY_KEY)
        }

        binding.categoriesIngredientsCardView.setOnClickListener {
            navigateToFragmentWithCategory(INGREDIENTS_CATEGORY_KEY)
        }

        binding.categoriesAlcoholCardView.setOnClickListener {
            navigateToFragmentWithCategory(ALCOHOL_CATEGORY_KEY)
        }
    }

    private fun navigateToFragmentWithCategory(categoryKey: String) {
        val bundle = Bundle()
        bundle.putString(CATEGORY_KEY, categoryKey)
        requireActivity().findNavController(R.id.fragment_container_view)
            .navigate(R.id.subcategoriesFragment, bundle)
    }

    companion object {
        private const val CATEGORY_CATEGORY_KEY = "c"
        private const val GLASS_CATEGORY_KEY = "g"
        private const val INGREDIENTS_CATEGORY_KEY = "i"
        private const val ALCOHOL_CATEGORY_KEY = "a"
        private const val CATEGORY_KEY = "categoryKey"
    }
}