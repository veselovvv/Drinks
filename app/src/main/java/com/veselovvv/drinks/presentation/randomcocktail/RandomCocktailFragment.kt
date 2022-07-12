package com.veselovvv.drinks.presentation.randomcocktail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.veselovvv.drinks.presentation.core.BaseCocktailInfoFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RandomCocktailFragment : BaseCocktailInfoFragment() {
    private val viewModel: RandomCocktailViewModel by viewModels()

    override fun fetchData() = viewModel.fetchRandomCocktail()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSwipeToRefresh()
        viewModel.observe(this) { ui ->
            ui.map(progressLayout)
            ui.map(
                view, nameTextView, categoryTextView, alcoholicTextView, glassTextView,
                instructionsTextView, photoImageView, ingredientsTextViews
            )
            ui.map(failLayout, failMessageTextView, failTryAgainButton, retry())
        }
        fetchData()
    }
}