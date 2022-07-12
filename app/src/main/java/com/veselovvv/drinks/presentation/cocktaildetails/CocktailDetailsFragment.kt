package com.veselovvv.drinks.presentation.cocktaildetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.veselovvv.drinks.presentation.core.BaseCocktailInfoFragment
import com.veselovvv.drinks.presentation.core.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailsFragment : BaseCocktailInfoFragment() {
    private val viewModel: CocktailDetailsViewModel by viewModels()

    override fun fetchData() = viewModel.fetchCocktailDetailsFromNetwork(viewModel.getCocktailName())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameTextView.text = viewModel.getCocktailName()
        categoryTextView.text = viewModel.getCocktailCategory()
        photoImageView.loadImage(view, viewModel.getCocktailPhotoUrl())

        setupSwipeToRefresh()
        viewModel.observe(this) { ui ->
            ui.map(progressLayout)
            ui.map(alcoholicTextView, glassTextView, instructionsTextView, ingredientsTextViews)
            ui.map(failLayout, failMessageTextView, failTryAgainButton, retry())
        }
        fetchData()
    }
}