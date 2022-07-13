package com.veselovvv.drinks.presentation.cocktaildetails

import com.google.android.material.textview.MaterialTextView

interface CocktailDetailsUiMapper {
    fun map(
        alcoholicTextView: MaterialTextView,
        glassTextView: MaterialTextView,
        instructionsTextView: MaterialTextView,
        ingredientsTextViews: List<MaterialTextView>
    )
}