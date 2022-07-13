package com.veselovvv.drinks.presentation.core

import android.view.LayoutInflater
import android.view.ViewGroup
import com.veselovvv.drinks.core.Retry
import com.veselovvv.drinks.databinding.FragmentCocktailDetailsBinding

abstract class BaseCocktailInfoFragment : BaseFragment<FragmentCocktailDetailsBinding>() {
    protected val progressLayout by lazy { binding.cocktailDetailsProgressLayout.root }
    protected val nameTextView by lazy { binding.cocktailDetailsName }
    protected val categoryTextView by lazy { binding.cocktailDetailsCategory }
    protected val alcoholicTextView by lazy { binding.cocktailDetailsAlcoholic }
    protected val glassTextView by lazy { binding.cocktailDetailsGlass }
    protected val instructionsTextView by lazy { binding.cocktailDetailsInstructions }
    protected val photoImageView by lazy { binding.cocktailDetailsPhoto }
    protected val failLayout by lazy { binding.cocktailDetailsFailLayout.root }
    protected val failMessageTextView by lazy { binding.cocktailDetailsFailLayout.failMessageTextView }
    protected val failTryAgainButton by lazy { binding.cocktailDetailsFailLayout.failTryAgainButton }
    protected val ingredientsTextViews by lazy { listOf(
        binding.cocktailDetailsIngredient1, binding.cocktailDetailsIngredient2,
        binding.cocktailDetailsIngredient3, binding.cocktailDetailsIngredient4,
        binding.cocktailDetailsIngredient5, binding.cocktailDetailsIngredient6,
        binding.cocktailDetailsIngredient7, binding.cocktailDetailsIngredient8,
        binding.cocktailDetailsIngredient9, binding.cocktailDetailsIngredient10
    ) }

    override fun setupViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCocktailDetailsBinding.inflate(inflater, container, false)

    abstract fun fetchData()
    abstract fun updateData()

    protected fun setupSwipeToRefresh() {
        val swipeToRefreshLayout = binding.cocktailDetailsSwipeToRefresh
        swipeToRefreshLayout.setOnRefreshListener {
            updateData()
            swipeToRefreshLayout.isRefreshing = false
        }
    }

    protected fun retry() = object : Retry {
        override fun tryAgain() = fetchData()
    }
}